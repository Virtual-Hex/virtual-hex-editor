package com.virtual_hex.jimgui;


import com.virtual_hex.data.*;
import com.virtual_hex.data.ext.ColumnSet;
import com.virtual_hex.data.ext.ColumnSetBody;
import com.virtual_hex.data.ext.ColumnSetHeader;
import com.virtual_hex.data.ext.ColumnSetRow;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.apache.commons.lang.ArrayUtils;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;


/**
 * Depends on JImGui
 *
 * This is used to loop and can be multiple nested to create specialized drawing
 *
 *
 * // Each serializer needs to be disposed of
 */
public class JImGuiUIDataDeserializer extends UIDataDeserializer<JImGui> {

    public static final JImGuiUIDataDeserializer DEFAULT_UI_DATA_DESERIALIZER
            = new JImGuiUIDataDeserializer("DEFAULT", new DeserializerMapFunction());

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JImGuiUIDataDeserializer.class);

    protected transient final DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, NativeBool>>> cachedBools = new HashMap<>();


    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, NativeInt>>> cachedInts = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, NativeFloat>>> cachedFloats = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, NativeDouble>>> cachedDoubles = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, byte[]>>> cachedBytes = new HashMap<>();

    protected transient Int2ObjectMap<JImVec4> cachedjimVec = new Int2ObjectOpenHashMap<>();

    // TODO String cache

    //TODO Make sure that these maps are full proof on fetching cached values
    protected Map[] maps = {
            cachedjimVec,
            cachedBools,
            cachedInts,
            cachedFloats,
            cachedDoubles
    };

    // THE INTENTION IS TO ALLOW NESTING OF HANDLING, BUT WE NEED TO FIRST SOLVE THE ISSUE WITH, DEALLOCATIONS,
    // With only serializer being used at the moment it is already dereference,

    public JImGuiUIDataDeserializer() {
        super("Anonymous");
    }

    public JImGuiUIDataDeserializer(String name) {
        super(name);
    }

    public JImGuiUIDataDeserializer(String name, Map<Class<?>, TypeDrawer<JImGui>> typeDrawers) {
        super(name, typeDrawers);
    }

    public JImGuiUIDataDeserializer(Map<Class<?>, TypeDrawer<JImGui>> typeDrawers) {
        super("Anonymous", typeDrawers);
    }

    public JImGuiUIDataDeserializer(Function<Map<Class<?>, TypeDrawer<JImGui>>, Map<Class<?>, TypeDrawer<JImGui>>> typeDrawers) {
        super(typeDrawers);
    }

    public JImGuiUIDataDeserializer(String name, Function<Map<Class<?>, TypeDrawer<JImGui>>, Map<Class<?>, TypeDrawer<JImGui>>> typeDrawers) {
        super(name, typeDrawers);
    }


//    /**
//     *
//     * @return a new JImGuiUIDataDeserializer with the same typeDrawers as the parents
//     */
//    public UIDataDeserializer<JImGui, T> newFromParent(){
//        JImGuiUIDataDeserializer clazzDrawer = new JImGuiUIDataDeserializer();
//        clazzDrawer.typeDrawers.putAll(this.typeDrawers);
//        return clazzDrawer;
//    }
//
//
//    public Map<Class<?>, TypeDrawer<JImGui, T>> getTypeDrawers() {
//        return typeDrawers;
//    }



    private static <T extends JImGuiUIDataDeserializer> void uiDeserializeWrapper(JImGui imGui, UIComponent uiComponent, T parentDeserializer) {
        UIDeserializerWrapper drawable = (UIDeserializerWrapper) uiComponent;
        UIDataDeserializer deserializer = drawable.deserializer;
        deserializer.draw(imGui, drawable.uiComponent, deserializer);
    }



    public void deallocateAll() {
        deallocatableObjectManager.deallocateAll();
    }


    public void clearCache(){
        deallocatableObjectManager.deallocateAll();
        for (int i = 0; i < maps.length; i++) {
            maps[i].clear();
        }
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @return
     */
    public NativeBool createBool(){
        NativeBool nativeValue = new NativeBool();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @return
     */
    public NativeShort createShort(){
        NativeShort nativeValue = new NativeShort();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @return
     */
    public NativeInt createInt(){
        NativeInt nativeValue = new NativeInt();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @return
     */
    public NativeLong createLong(){
        NativeLong nativeValue = new NativeLong();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @return
     */
    public NativeFloat createFloat(){
        NativeFloat nativeValue = new NativeFloat();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @return
     */
    public NativeDouble createDouble(){
        NativeDouble nativeValue = new NativeDouble();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @return
     */
    @NativeExchange
    public JImVec4 createJImVec4(float x, float y, float z, float w){
        JImVec4 jImVec4 = new JImVec4(x, y, z, w);
        deallocatableObjectManager.add(jImVec4);
        return jImVec4;
    }

    // TODO Register method
    @NativeExchange
    public void drawReflectiveInputColumns(JImGui imGui, Object drawable, UIDataDeserializer<JImGui> parentDrawer){
        Class<?> aClass = drawable.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        UIComponent[] columns = new UIComponent[declaredFields.length];
        ColumnSet columnSet = new ColumnSet(
                new ColumnSetHeader(aClass + " - hc:" + drawable.hashCode(), true,
                        "Value & Field Name", "Type", "Size (b)"),
                new ColumnSetBody(new ColumnSetRow(columns))
        );

        int declaredFieldsLength = declaredFields.length;
        for (int i = 0; i < declaredFieldsLength; i++) {
            Field field = declaredFields[i];

            try {

            } catch (Exception e){
                e.printStackTrace();
            }
//
//            typeHandlerMap.computeIfPresent(clazz, (aClass1, jImGuiTypeHandler) -> {
//                jImGuiTypeHandler.handle(imGui, declaredFieldsLength, field, instanceId, objectToDraw);
//                return jImGuiTypeHandler;
//            });
        }
    }

    public NativeBool getNativeBool(String fieldName, Object object){
        return cachedBools
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> createBool());
    }

    public NativeInt getNativeInt(String fieldName, Object object){
        return cachedInts
                .computeIfAbsent(object.getClass(),  aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> createInt());
    }

    public NativeFloat getNativeFloat(String fieldName, Object object){
        return cachedFloats
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> createFloat());
    }

    public NativeDouble getNativeDouble(String fieldName, Object object){
        return cachedDoubles
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> createDouble());
    }

    public byte[] getStringByteCache(String fieldName, Object object, int bufferSize){
        return cachedBytes
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(fieldName, name -> new byte[bufferSize]);
    }

    public static void endTabBar(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        imGui.endTabBar();
    }

    public static void endTabItem(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        imGui.endTabItem();
    }

    public static void seperator(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        imGui.separator();
    }


    public static void nextColumn(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        imGui.nextColumn();
    }

    public static void columns(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        Columns drawable = (Columns) uiComponent;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public static void drawOpenableFlags(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        OpenableFlags openable = (OpenableFlags) uiComponent;
        switch (openable.type) {
            case WINDOW: {
                if(openable.open){
                    // Not clipped or collapsed
                    boolean visible = imGui.begin(openable.label);
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                    }
                    imGui.end();
                }
                break;
            }
            case WINDOW_EXITABLE: {
                if (openable.open) {
                    NativeBool value = parentDrawer.getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = imGui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                    }
                    imGui.end();
                }
                break;
            }
            case TAB_BAR: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = imGui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                        // We want to let the type specific know its time to pop out
                        imGui.endTabBar();
                    }
                }
                break;
            }
            case TAB_ITEM: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = imGui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                        // We want to let the type specific know its time to pop out
                        imGui.endTabItem();
                    }
                }
                break;
            }
            case TAB_ITEM_EXITABLE: {
                if(openable.open){
                    NativeBool value = parentDrawer.getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = imGui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                        // We want to let the type specific know its time to pop out
                        imGui.endTabItem();
                    }
                }
                break;

            }
            case COLLAPSABLE_HEADER: {
                if (openable.open) {
                    // Not clipped or collapsed
                    boolean visible = imGui.begin(openable.label);
                    if (visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                        // We want to let the type specific know its time to pop out
                    }
                }
                break;
            }
            case COLLAPSABLE_HEADER_EXITABLE: {
                if(openable.open){
                    NativeBool value = parentDrawer.getNativeBool("open", openable);
                    // Not clipped or collapsed
                    value.modifyValue(openable.open);
                    boolean visible = imGui.begin(openable.label, value, openable.flags);
                    openable.open = value.accessValue();
                    if(visible) { // TODO Maybe remove this open check
                        processUiDataList(imGui, openable.uiDataArray, parentDrawer);
                        // We want to let the type specific know its time to pop out
                    }
                }
                break;
            }
        }
    }

    // This wraps a field mapper
    public static void checkbox(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        CheckBox drawable = (CheckBox) uiComponent;
        NativeBool value =  parentDrawer.getNativeBool("value", drawable);
        imGui.checkbox(drawable.label, value);
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputText(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        TextInput drawable = (TextInput) uiComponent;
        byte[] value =  parentDrawer.getStringByteCache("value", drawable, drawable.bufferSize);
        copyStringIntoBuffer(drawable.label, value);
        imGui.inputText(drawable.label, value, drawable.flags);
        drawable.value = new String(value, 0, bufferEndIndex(value)); // TODO Cache changes to prevent string being re referenced at end?
    }


    protected static void copyStringIntoBuffer(String textInput, byte[] stringBuffer){
        Arrays.fill(stringBuffer, (byte) 0);
        System.arraycopy(textInput.getBytes(), 0, stringBuffer, 0, textInput.length());
    }

    public static int getBufferEndIndex(byte[] stringBuffer){
        return bufferEndIndex(stringBuffer);
    }

    public static int bufferEndIndex(byte[] data){
        for(int i = 0; i < data.length; i++) {
            if (data[i] == 0) return i;
        }
        return 0;
    }

    @NativeExchange
    public static void inputInt(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputInt drawable = (InputInt) uiComponent;
        NativeInt value =  parentDrawer.getNativeInt("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputInt(drawable.label, value);
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputIntStepped(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputIntStepped drawable = (InputIntStepped) uiComponent;
        NativeInt value =  parentDrawer.getNativeInt("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputInt(drawable.label, value, drawable.step, drawable.stepFast, drawable.flags);
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputFloat(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputFloat drawable = (InputFloat) uiComponent;
        NativeFloat value =  parentDrawer.getNativeFloat("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputFloat(drawable.label, value);
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputFloatStepped(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputFloatStepped drawable = (InputFloatStepped) uiComponent;
        NativeFloat value =  parentDrawer.getNativeFloat("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputFloat(drawable.label, value, drawable.step, drawable.stepFast);// TODO Format
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputDouble(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputDouble drawable = (InputDouble) uiComponent;
        NativeDouble value =  parentDrawer.getNativeDouble("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputDouble(drawable.label, value);
        drawable.value = value.accessValue();
    }

    @NativeExchange
    public static void inputDoubleStepped(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        InputDoubleStepped drawable = (InputDoubleStepped) uiComponent;
        NativeDouble value =  parentDrawer.getNativeDouble("value", drawable);
        value.modifyValue(drawable.value);
        imGui.inputDouble(drawable.label, value, drawable.step, drawable.stepFast);// TODO Format
        drawable.value = value.accessValue();
    }

    public static void button(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        Button drawable = (Button) uiComponent;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void beginMenu(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void beginMenuItem(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {}

    public static void treeNodeEx(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        TreeNodeEx drawable = (TreeNodeEx) uiComponent;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            processUiDataList(imGui, drawable.UIDataArray, parentDrawer);
            imGui.treePop();
        }
    }

    @NativeExchange
    public static void colorText(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        ColorText drawable = (ColorText) uiComponent;
        Vec4 color = drawable.color;
        JImVec4 jImVec4 = (JImVec4) parentDrawer.cachedjimVec.computeIfAbsent(color.hashCode(), value -> parentDrawer.createJImVec4(color.x, color.y, color.z, color.w));
        imGui.textColored(jImVec4, drawable.text);
    }

    public static void text(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        Text drawable = (Text) uiComponent;
        imGui.text(drawable.text);
    }

    @NativeExchange
    public static void selectable(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        Selectable drawable = (Selectable) uiComponent;
        NativeBool value =  parentDrawer.getNativeBool("selected", drawable);
        //  returning the state true when open or false when unselected
        //  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
        value.modifyValue(drawable.selected);
        if(imGui.selectable(drawable.label, value, drawable.flags, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
        drawable.selected = value.accessValue();
    }

    public static void sameLine(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer){
        SameLine drawable = (SameLine) uiComponent;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void newLine(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        imGui.newLine();
    }

    private static void processUiDataList(JImGui imGui, UIComponentArray drawable, JImGuiUIDataDeserializer parentDrawer) {
        processUiDataList(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    private static void processUiDataList(JImGui imGui, UIComponent[] drawables, Queue<UIComponent> addWindowQueue, Queue<UIComponent> removeWindowQueue, JImGuiUIDataDeserializer parentDrawer) {
        for (UIComponent element; (element = addWindowQueue.poll()) != null;){
            ArrayUtils.add(drawables, element);
        }

        for (UIComponent element; (element = removeWindowQueue.poll()) != null;){
            ArrayUtils.removeElement(drawables, element);
        }
        for (int i = 0; i < drawables.length; i++) {
            UIComponent drawable = drawables[i];
            if(drawable != null){
                parentDrawer.draw(imGui, drawable, parentDrawer);
            }
        }
    }

    public static void jaweDrawables(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        UIComponentArray drawable = (UIComponentArray) uiComponent;
        processUiDataList(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void dummy(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        Dummy drawable = (Dummy) uiComponent;
        imGui.dummy(drawable.width, drawable.height);
    }

    public static void invisibleButton(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        InvisibleButton drawable = (InvisibleButton) uiComponent;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void smallButton(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        SmallButton drawable = (SmallButton) uiComponent;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void spacing(JImGui imGui, UIComponent uiComponent, JImGuiUIDataDeserializer parentDrawer) {
        imGui.spacing();
    }

}
