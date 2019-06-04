package com.virtual_hex.jimgui;


import com.virtual_hex.data.*;
import com.virtual_hex.data.ext.ColumnSet;
import com.virtual_hex.data.ext.ColumnSetBody;
import com.virtual_hex.data.ext.ColumnSetHeader;
import com.virtual_hex.data.ext.ColumnSetRow;
import com.virtual_hex.jimgui.wrappers.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;


/**
 * Depends on JImGui
 *
 * This is used to loop and can be multiple nested to create specialized drawing
 */
public class JImGuiUIDataDeserializer<T extends JImGuiUIDataDeserializer> implements UIDataDeserializer<JImGui, T> {

    public static final JImGuiUIDataDeserializer DEFAULT_UI_DATA_DESERIALIZER
            = new JImGuiUIDataDeserializer(true, "DEFAULT");

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JImGuiUIDataDeserializer.class);


    protected transient final DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, DataFieldMapper<NativeBool>>>> cachedMappersBool = new HashMap<>();


    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, DataFieldMapper<NativeInt>>>> cachedMappersInt = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, DataFieldMapper<NativeFloat>>>> cachedMappersFloat = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, DataFieldMapper<NativeDouble>>>> cachedMappersDouble = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    protected transient Map<Class<?>, Int2ObjectMap<Map<String, DataFieldMapper<byte[]>>>> cachedMappersBytes = new HashMap<>();

    protected transient Int2ObjectMap<JImVec4> cachedjimVec = new Int2ObjectOpenHashMap<>();

    protected Map[] maps = {
            cachedMappersBool,
            cachedMappersInt,
            cachedMappersFloat,
            cachedMappersDouble,
            cachedMappersBytes,
            cachedjimVec
    };

    public Map<Class<?>, TypeDrawer<JImGui, T>> typeDrawers;
    public String name;


    public JImGuiUIDataDeserializer() {
        this.name = "";
        this.typeDrawers = new HashMap<>();
    }

    public JImGuiUIDataDeserializer(boolean useDefaultDrawer, String name) {
        this.typeDrawers = new HashMap<>();
        if(useDefaultDrawer){
            init();
        } else {

        }
        this.name = name;
    }

    /**
     *
     * @return a new JImGuiUIDataDeserializer with the same typeDrawers as the parents
     */
    @Override
    public UIDataDeserializer<JImGui, T> newFromParent(){
        JImGuiUIDataDeserializer clazzDrawer = new JImGuiUIDataDeserializer();
        clazzDrawer.typeDrawers.putAll(this.typeDrawers);
        return clazzDrawer;
    }

    @Override
    public Map<Class<?>, TypeDrawer<JImGui, T>> getTypeDrawers() {
        return typeDrawers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void init() {
        // TODO All items

        typeDrawers.put(BeginMenu.class, JImGuiUIDataDeserializer::beginMenu);

        typeDrawers.put(TabBar.class, JImGuiUIDataDeserializer::beginTabBar);
        typeDrawers.put(BeginTabItem.class, JImGuiUIDataDeserializer::beginTabItem);
        typeDrawers.put(BeginTabItemExitable.class, JImGuiUIDataDeserializer::beginTabItemExitable);
        typeDrawers.put(Button.class, JImGuiUIDataDeserializer::button);
        typeDrawers.put(CheckBox.class, JImGuiUIDataDeserializer::checkbox);
        typeDrawers.put(CollapsingHeader.class, JImGuiUIDataDeserializer::collapsingHeader);
        typeDrawers.put(ColorText.class, JImGuiUIDataDeserializer::colorText);
        typeDrawers.put(Columns.class, JImGuiUIDataDeserializer::columns);
        typeDrawers.put(Dummy.class, JImGuiUIDataDeserializer::dummy);
        // TODO COMBO
        typeDrawers.put(InputDouble.class, JImGuiUIDataDeserializer::inputDouble);
        typeDrawers.put(InputDoubleStepped.class, JImGuiUIDataDeserializer::inputDoubleStepped);
        typeDrawers.put(InputFloat.class, JImGuiUIDataDeserializer::inputFloat);
        typeDrawers.put(InputFloatStepped.class, JImGuiUIDataDeserializer::inputFloatStepped);
        typeDrawers.put(InputInt.class, JImGuiUIDataDeserializer::inputInt);
        typeDrawers.put(InputIntStepped.class, JImGuiUIDataDeserializer::inputIntStepped);

        typeDrawers.put(InvisibleButton.class, JImGuiUIDataDeserializer::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawers.put(NewLine.class, JImGuiUIDataDeserializer::newLine);
        typeDrawers.put(NextColumn.class, JImGuiUIDataDeserializer::nextColumn);
        // TODO OPEN POPUP
        typeDrawers.put(SameLine.class, JImGuiUIDataDeserializer::sameLine);
        typeDrawers.put(Selectable.class, JImGuiUIDataDeserializer::selectable);
        typeDrawers.put(Separator.class, JImGuiUIDataDeserializer::seperator);
        typeDrawers.put(SmallButton.class, JImGuiUIDataDeserializer::smallButton);
        typeDrawers.put(Spacing.class, JImGuiUIDataDeserializer::spacing);
        typeDrawers.put(Text.class, JImGuiUIDataDeserializer::text);
        typeDrawers.put(TextInput.class, JImGuiUIDataDeserializer::inputText);
        typeDrawers.put(TreeNodeEx.class, JImGuiUIDataDeserializer::treeNodeEx);
        typeDrawers.put(Window.class, JImGuiUIDataDeserializer::window);

        // This will stay because its needed to structured the drawing
        typeDrawers.put(UIDataList.class, JImGuiUIDataDeserializer::jaweDrawables);

        typeDrawers.put(ArrayList.class, JImGuiUIDataDeserializer::list);
        typeDrawers.put(LinkedList.class, JImGuiUIDataDeserializer::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawers.put(List.class, JImGuiUIDataDeserializer::list);

        typeDrawers.put(UIApp.class, JImGuiUIDataDeserializer::uiApp);
        typeDrawers.put(UIDeserializerWrapper.class, JImGuiUIDataDeserializer::uiDeserializeWrapper);


        // Editable
    }


    private static <T extends JImGuiUIDataDeserializer> void uiDeserializeWrapper(JImGui imGui, UIData drawable, JImGuiUIDataDeserializer parentDeserializer) {
        UIDeserializerWrapper wrapper = (UIDeserializerWrapper) drawable;
        UIDataDeserializer deserializer = wrapper.deserializer;
        wrapper.deserializer.draw(deserializer, ((UIDeserializerWrapper) drawable).uiData, deserializer);
    }


    private static <T extends JImGuiUIDataDeserializer> void uiApp(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDeserializer) {
        UIApp drawable = (UIApp) uiData;
        parentDeserializer.draw(imGui, drawable.deserializerWrapper.uiData, parentDeserializer);
    }

    @Override
    public void close() {
        deallocatableObjectManager.close();
    }

    @Override
    public void deallocateAll() {
        deallocatableObjectManager.deallocateAll();
    }

    @Override
    public void clearCache(){
        deallocatableObjectManager.deallocateAll();
        for (int i = 0; i < maps.length; i++) {
            maps[i].clear();
        }
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeBool createBool(boolean value){
        NativeBool nativeValue = new NativeBool();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeShort createShort(short value){
        NativeShort nativeValue = new NativeShort();
        nativeValue.modifyValue(value);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeInt createInt(int value){
        NativeInt nativeValue = new NativeInt();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     * @param value
     * @return
     */
    public NativeLong createLong(long value){
        NativeLong nativeValue = new NativeLong();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @param value
     * @return
     */
    public NativeFloat createFloat(float value){
        NativeFloat nativeValue = new NativeFloat();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @param value
     * @return
     */
    public NativeDouble createDouble(double value){
        NativeDouble nativeValue = new NativeDouble();
        nativeValue.modifyValue(value);
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    /**
     * Will be deallocated after this deallocateNativeObject0() is called
     * @return
     */
    public JImVec4 createJImVec4(float x, float y, float z, float w){
        JImVec4 jImVec4 = new JImVec4(x, y, z, w);
        deallocatableObjectManager.add(jImVec4);
        return jImVec4;
    }

    public void deallocateNativeObject0() {
        deallocatableObjectManager.close();
    }

    // TODO Register method

    public void drawReflectiveInputColumns(JImGui imGui, Object drawable, JImGuiUIDataDeserializer parentDrawer){
        Class<?> aClass = drawable.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        UIData[] columns = new UIData[declaredFields.length];
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

    @Override
    public void draw(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDeserializer) {
        Class<?> aClass = uiData.getClass();
        TypeDrawer<JImGui, T> objectDrawer = typeDrawers.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
//        LOGGER.debug("Drawing a class {}, type {}", drawable, aClass);
        objectDrawer.draw(imGui, uiData, (T) parentDeserializer);
    }

    public static void list(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        List<UIData> list = (List<UIData>) uiData;
        list.forEach(d -> parentDrawer.draw(imGui, d, parentDrawer));
    }

    // Check iterations and sub-typing through testing
    public TypeDrawer<JImGui, T> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        TypeDrawer<JImGui, T> biConsumer = typeDrawers.get(aSubTypeClazz);
        return biConsumer == null ? JImGuiUIDataDeserializer::emptyDrawable : biConsumer;
    }

    public static void beginTabBar(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        TabBar drawable = (TabBar) uiData;
        boolean selected = imGui.beginTabBar(drawable.label, drawable.flags);
        if(selected){
            jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
            imGui.endTabBar();
        }
    }

    public static void beginTabItem(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        BeginTabItem drawable = (BeginTabItem) uiData;
        boolean selected = imGui.beginTabItem(drawable.label);
        if(selected){
            jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
            imGui.endTabItem();
        }
    }

    public static void beginTabItemExitable(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        BeginTabItemExitable drawable = (BeginTabItemExitable) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper =  parentDrawer.getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            boolean selected = imGui.beginTabItem(drawable.label, mapper.getNativeData(), drawable.flags);
            if(selected){
                jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
                imGui.endTabItem();
            }
            // make sure the field is updated at the end
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public DataFieldMapper<NativeBool> getDataFieldMapperBool(Field field, Object object){
        return cachedMappersBool
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeBooleanDataFieldMapper(createBool(true), field, object));
    }

    public DataFieldMapper<NativeInt> getDataFieldMapperInt(Field field, Object object){
        return cachedMappersInt
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeIntDataFieldMapper(createInt(0), field, object));
    }

    public DataFieldMapper<NativeFloat> getDataFieldMapperFloat(Field field, Object object){
        return cachedMappersFloat
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeFloatDataFieldMapper(createFloat(0f), field, object));
    }

    public DataFieldMapper<NativeDouble> getDataFieldMapperDouble(Field field, Object object){
        return cachedMappersDouble
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeDoubleDataFieldMapper(createDouble(0d), field, object));
    }

    public DataFieldMapper<byte[]> getDataFieldMapperString(Field field, Object object){
        return cachedMappersBytes
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new StringDataFieldMapper(field, object));
    }

    public static void endTabBar(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        imGui.endTabBar();
    }

    public static void endTabItem(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        imGui.endTabItem();
    }

    public static void seperator(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        imGui.separator();
    }


    public static void nextColumn(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        imGui.nextColumn();
    }

    public static void columns(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        Columns drawable = (Columns) uiData;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public static void window(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        Window drawable = (Window) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper = parentDrawer.getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            if(imGui.begin(drawable.label, mapper.getNativeData(), drawable.flags)) {
                mapper.setFieldFromNative();
                jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
                drawable.onActivation.handle(drawable, parentDrawer);
                imGui.end();// May need to be moved up into the if loop
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // This wraps a field mapper
    public static void checkbox(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        CheckBox drawable = (CheckBox) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeBool> mapper =  parentDrawer.getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            imGui.checkbox(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputText(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        TextInput drawable = (TextInput) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<byte[]> mapper =  parentDrawer.getDataFieldMapperString(field, drawable);
            mapper.setNativeFromField();
            imGui.inputText(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputInt(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputInt drawable = (InputInt) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeInt> mapper =  parentDrawer.getDataFieldMapperInt(field, drawable);
            mapper.setNativeFromField();
            imGui.inputInt(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputIntStepped(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputIntStepped drawable = (InputIntStepped) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeInt> mapper =  parentDrawer.getDataFieldMapperInt(field, drawable);
            mapper.setNativeFromField();
            imGui.inputInt(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast, drawable.flags);
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputFloat(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputFloat drawable = (InputFloat) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeFloat> mapper =  parentDrawer.getDataFieldMapperFloat(field, drawable);
            mapper.setNativeFromField();
            imGui.inputFloat(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputFloatStepped(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputFloatStepped drawable = (InputFloatStepped) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeFloat> mapper =  parentDrawer.getDataFieldMapperFloat(field, drawable);
            mapper.setNativeFromField();
            imGui.inputFloat(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast);// TODO Format
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public static void inputDouble(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputDouble drawable = (InputDouble) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeDouble> mapper =  parentDrawer.getDataFieldMapperDouble(field, drawable);
            mapper.setNativeFromField();
            imGui.inputDouble(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputDoubleStepped(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        InputFloatStepped drawable = (InputFloatStepped) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeDouble> mapper =  parentDrawer.getDataFieldMapperDouble(field, drawable);
            mapper.setNativeFromField();
            imGui.inputDouble(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast);// TODO Format
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void button(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        Button drawable = (Button) uiData;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }
    public static void beginMenu(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void beginMenuItem(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {}

    public static void collapsingHeaderExitable(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        CollapsingHeaderExitable drawable = (CollapsingHeaderExitable) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper =  parentDrawer.getDataFieldMapperBool(field, drawable);

            mapper.setNativeFromField();
            boolean isOpen = imGui.collapsingHeader(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
            if(isOpen){
                jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void collapsingHeader(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        CollapsingHeader drawable = (CollapsingHeader) uiData;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
        }
    }

    public static void treeNodeEx(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        TreeNodeEx drawable = (TreeNodeEx) uiData;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            jaweDrawableProcess(imGui, drawable.UIDataList, parentDrawer);
            imGui.treePop();
        }
    }

    public static void colorText(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        ColorText drawable = (ColorText) uiData;
        Vec4 color = drawable.color;
        JImVec4 jImVec4 = (JImVec4) parentDrawer.cachedjimVec.computeIfAbsent(color.hashCode(), value -> parentDrawer.createJImVec4(color.x, color.y, color.z, color.w));
        imGui.textColored(jImVec4, drawable.text);
    }

    public static void text(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        Text drawable = (Text) uiData;
        imGui.text(drawable.text);
    }

    public static void selectable(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        Selectable drawable = (Selectable) uiData;
        Field field;
        try {
            field = drawable.getClass().getField("selected");
            DataFieldMapper<NativeBool> mapper =  parentDrawer.getDataFieldMapperBool(field, drawable);
            //  returning the state true when open or false when unselected
            //  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
            mapper.setNativeFromField();
            if(imGui.selectable(drawable.label, mapper.getNativeData(), drawable.flags, drawable.width, drawable.height)){
                mapper.setFieldFromNative();
                drawable.onActivation.handle(drawable, parentDrawer);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void sameLine(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer){
        SameLine drawable = (SameLine) uiData;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void newLine(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        imGui.newLine();
    }

    public static void jaweDrawableProcess(JImGui imGui, UIDataList drawable, JImGuiUIDataDeserializer parentDrawer) {
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void jaweDrawableProcess(JImGui imGui, List<UIData> drawables, Queue<UIData> addWindowQueue, Queue<UIData> removeWindowQueue, JImGuiUIDataDeserializer parentDrawer) {
        for (UIData element; (element = addWindowQueue.poll()) != null;){
            drawables.add(element);
        }

        for (UIData element; (element = removeWindowQueue.poll()) != null;){
            drawables.remove(element);
        }

        drawables.forEach((nestedDrawable) -> parentDrawer.draw(imGui, nestedDrawable, parentDrawer));
    }

    public static void jaweDrawables(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        UIDataList drawable = (UIDataList) uiData;
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void dummy(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        Dummy drawable = (Dummy) uiData;
        imGui.dummy(drawable.width, drawable.height);
    }

    public static void invisibleButton(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        InvisibleButton drawable = (InvisibleButton) uiData;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void smallButton(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        SmallButton drawable = (SmallButton) uiData;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void spacing(JImGui imGui, UIData uiData, JImGuiUIDataDeserializer parentDrawer) {
        imGui.spacing();
    }

}
