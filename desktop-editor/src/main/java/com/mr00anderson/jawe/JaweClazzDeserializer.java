package com.mr00anderson.jawe;

import com.mr00anderson.data.*;
import com.mr00anderson.data.ext.JaweColumnSet;
import com.mr00anderson.data.ext.JaweColumnSetBody;
import com.mr00anderson.data.ext.JaweColumnSetHeader;
import com.mr00anderson.data.ext.JaweColumnSetRow;
import com.mr00anderson.jawe.wrappers.*;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;


/**
 * Depends on JImGui // Could be extracted out one layer and friendly to any backend in the future
 */
public class JaweClazzDeserializer implements ClazzDeserializer<JImGui> {

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JaweClazzDeserializer.class);


    /**
     * These get cached first cycle through
     */
    public static transient Map<Class<?>, Int2ObjectMap<HashMap<String, DataFieldMapper<NativeBool>>>> cachedMappersBool = new HashMap<>();


    /**
     * These get cached first cycle through
     */
    public static transient Map<Class<?>, Int2ObjectMap<HashMap<String, DataFieldMapper<NativeInt>>>> cachedMappersInt = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    public static transient Map<Class<?>, Int2ObjectMap<HashMap<String, DataFieldMapper<NativeFloat>>>> cachedMappersFloat = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    public static transient Map<Class<?>, Int2ObjectMap<HashMap<String, DataFieldMapper<NativeDouble>>>> cachedMappersDouble = new HashMap<>();

    /**
     * These get cached first cycle through
     */
    public static transient Map<Class<?>, Int2ObjectMap<HashMap<String, DataFieldMapper<byte[]>>>> cachedMappersBytes = new HashMap<>();

    public static transient Int2ObjectMap<JImVec4> cachedjimVec = new Int2ObjectOpenHashMap<>();

    public Map<Class<?>, TypeDrawer<JImGui>> typeDrawers;
    public String name;


    public JaweClazzDeserializer() {
        this.name = "";
        this.typeDrawers = new HashMap<>();
    }

    public JaweClazzDeserializer(boolean useDefaultDrawer, String name) {
        this.typeDrawers = new HashMap<>();
        if(useDefaultDrawer){
            init();
        } else {
        }
        this.name = name;
    }

    /**
     *
     * @return a new JaweClazzDeserializer with the same typeDrawers as the parents
     */
    @Override
    public JaweClazzDeserializer newFromParent(){
        JaweClazzDeserializer clazzDrawer = new JaweClazzDeserializer();
        clazzDrawer.typeDrawers.putAll(this.typeDrawers);
        return clazzDrawer;
    }

    @Override
    public Map<Class<?>, TypeDrawer<JImGui>> getTypeDrawers() {
        return typeDrawers;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void init() {
        typeDrawers.put(BeginMenu.class, JaweClazzDeserializer::beginMenu);

        typeDrawers.put(TabBar.class, JaweClazzDeserializer::beginTabBar);
        typeDrawers.put(BeginTabItem.class, JaweClazzDeserializer::beginTabItem);
        typeDrawers.put(BeginTabItemExitable.class, JaweClazzDeserializer::beginTabItemExitable);
        typeDrawers.put(Button.class, JaweClazzDeserializer::button);
        typeDrawers.put(CheckBox.class, JaweClazzDeserializer::checkbox);
        typeDrawers.put(CollapsingHeader.class, JaweClazzDeserializer::collapsingHeader);
        typeDrawers.put(ColorText.class, JaweClazzDeserializer::colorText);
        typeDrawers.put(Columns.class, JaweClazzDeserializer::columns);
        typeDrawers.put(Dummy.class, JaweClazzDeserializer::dummy);
        // TODO COMBO
        typeDrawers.put(InputDouble.class, JaweClazzDeserializer::inputDouble);
        typeDrawers.put(InputDoubleStepped.class, JaweClazzDeserializer::inputDoubleStepped);
        typeDrawers.put(InputFloat.class, JaweClazzDeserializer::inputFloat);
        typeDrawers.put(InputFloatStepped.class, JaweClazzDeserializer::inputFloatStepped);
        typeDrawers.put(InputInt.class, JaweClazzDeserializer::inputInt);
        typeDrawers.put(InputIntStepped.class, JaweClazzDeserializer::inputIntStepped);

        typeDrawers.put(InvisibleButton.class, JaweClazzDeserializer::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawers.put(NewLine.class, JaweClazzDeserializer::newLine);
        typeDrawers.put(NextColumn.class, JaweClazzDeserializer::nextColumn);
        // TODO OPEN POPUP
        typeDrawers.put(SameLine.class, JaweClazzDeserializer::sameLine);
        typeDrawers.put(Selectable.class, JaweClazzDeserializer::selectable);
        typeDrawers.put(Separator.class, JaweClazzDeserializer::seperator);
        typeDrawers.put(SmallButton.class, JaweClazzDeserializer::smallButton);
        typeDrawers.put(Spacing.class, JaweClazzDeserializer::spacing);
        typeDrawers.put(Text.class, JaweClazzDeserializer::text);
        typeDrawers.put(TextInput.class, JaweClazzDeserializer::inputText);
        typeDrawers.put(TreeNodeEx.class, JaweClazzDeserializer::treeNodeEx);
        typeDrawers.put(Window.class, JaweClazzDeserializer::window);

        // This will stay because its needed to structured the drawing
        typeDrawers.put(Drawables.class, JaweClazzDeserializer::jaweDrawables);

        typeDrawers.put(ArrayList.class, JaweClazzDeserializer::list);
        typeDrawers.put(LinkedList.class, JaweClazzDeserializer::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawers.put(List.class, JaweClazzDeserializer::list);



        // Editable
    }

    // TODO Register method

    public void drawReflectiveInputColumns(JImGui imGui, Object drawable, JaweClazzDeserializer parentDrawer){
        Class<?> aClass = drawable.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();

        Object[] columns = new Object[declaredFields.length];
        JaweColumnSet columnSet = new JaweColumnSet(
                new JaweColumnSetHeader(aClass + " - hc:" + drawable.hashCode(), true,
                        "Value & Field Name", "Type", "Size (b)"),
                new JaweColumnSetBody(new JaweColumnSetRow(columns))
        );

        int declaredFieldsLength = declaredFields.length;
        for (int i = 0; i < declaredFieldsLength; i++) {
            Field field = declaredFields[i];


            try {
                draw(imGui, field.get(drawable), parentDrawer);
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
    public void draw(JImGui imGui, Object drawable, ClazzDeserializer<JImGui> parentDrawer) {
        Class<?> aClass = drawable.getClass();
        TypeDrawer<JImGui> objectDrawer = typeDrawers.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
//        LOGGER.debug("Drawing a class {}, type {}", drawable, aClass);
        objectDrawer.draw(imGui, drawable, parentDrawer);
    }

    public static void list(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        List list = (List) drawable0;
        list.forEach(d -> parentDrawer.draw(imGui, d, parentDrawer));
    }

    // Check iterations and sub-typing through testing
    public TypeDrawer<JImGui> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        TypeDrawer<JImGui> biConsumer = typeDrawers.get(aSubTypeClazz);
        return biConsumer == null ? JaweClazzDeserializer::emptyDrawable : biConsumer;
    }

    public static void beginTabBar(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        TabBar drawable = (TabBar) drawable0;
        boolean selected = imGui.beginTabBar(drawable.label, drawable.flags);
        if(selected){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.endTabBar();
        }
    }

    public static void beginTabItem(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        BeginTabItem drawable = (BeginTabItem) drawable0;
        boolean selected = imGui.beginTabItem(drawable.label);
        if(selected){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.endTabItem();
        }
    }

    public static void beginTabItemExitable(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        BeginTabItemExitable drawable = (BeginTabItemExitable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper = getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            boolean selected = imGui.beginTabItem(drawable.label, mapper.getNativeData(), drawable.flags);
            if(selected){
                jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
                imGui.endTabItem();
            }
            // make sure the field is updated at the end
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public static DataFieldMapper<NativeBool> getDataFieldMapperBool(Field field, Object object){
        return cachedMappersBool
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeBooleanDataFieldMapper(field, object));
    }

    public static DataFieldMapper<NativeInt> getDataFieldMapperInt(Field field, Object object){
        return cachedMappersInt
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeIntDataFieldMapper(field, object));
    }

    public static DataFieldMapper<NativeFloat> getDataFieldMapperFloat(Field field, Object object){
        return cachedMappersFloat
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeFloatDataFieldMapper(field, object));
    }

    public static DataFieldMapper<NativeDouble> getDataFieldMapperDouble(Field field, Object object){
        return cachedMappersDouble
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new NativeDoubleDataFieldMapper(field, object));
    }

    public static DataFieldMapper<byte[]> getDataFieldMapperString(Field field, Object object){
        return cachedMappersBytes
                .computeIfAbsent(object.getClass(), aClass -> new Int2ObjectOpenHashMap<>())
                .computeIfAbsent(object.hashCode(), value -> new HashMap<>())
                .computeIfAbsent(field.getName(), name -> new StringDataFieldMapper(field, object));
    }

    public static void endTabBar(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        imGui.endTabBar();
    }

    public static void endTabItem(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        imGui.endTabItem();
    }

    public static void seperator(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        imGui.separator();
    }


    public static void nextColumn(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        imGui.nextColumn();
    }

    public static void columns(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        Columns drawable = (Columns) drawable0;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public static void window(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        Window drawable = (Window) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper = getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            if(imGui.begin(drawable.label, mapper.getNativeData(), drawable.flags)) {
                mapper.setFieldFromNative();
                jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
                drawable.onActivation.handle(drawable, parentDrawer);
                imGui.end();// May need to be moved up into the if loop
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // This wraps a field mapper
    public static void checkbox(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        CheckBox drawable = (CheckBox) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeBool> mapper = getDataFieldMapperBool(field, drawable);
            mapper.setNativeFromField();
            imGui.checkbox(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputText(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        TextInput drawable = (TextInput) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<byte[]> mapper = getDataFieldMapperString(field, drawable);
            mapper.setNativeFromField();
            imGui.inputText(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputInt(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputInt drawable = (InputInt) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeInt> mapper = getDataFieldMapperInt(field, drawable);
            mapper.setNativeFromField();
            imGui.inputInt(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputIntStepped(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputIntStepped drawable = (InputIntStepped) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeInt> mapper = getDataFieldMapperInt(field, drawable);
            mapper.setNativeFromField();
            imGui.inputInt(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast, drawable.flags);
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputFloat(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputFloat drawable = (InputFloat) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeFloat> mapper = getDataFieldMapperFloat(field, drawable);
            mapper.setNativeFromField();
            imGui.inputFloat(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputFloatStepped(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputFloatStepped drawable = (InputFloatStepped) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeFloat> mapper = getDataFieldMapperFloat(field, drawable);
            mapper.setNativeFromField();
            imGui.inputFloat(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast);// TODO Format
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    public static void inputDouble(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputDouble drawable = (InputDouble) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeDouble> mapper = getDataFieldMapperDouble(field, drawable);
            mapper.setNativeFromField();
            imGui.inputDouble(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void inputDoubleStepped(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        InputFloatStepped drawable = (InputFloatStepped) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("value");
            DataFieldMapper<NativeDouble> mapper = getDataFieldMapperDouble(field, drawable);
            mapper.setNativeFromField();
            imGui.inputDouble(drawable.label, mapper.getNativeData(), drawable.step, drawable.stepFast);// TODO Format
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void button(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        Button drawable = (Button) drawable0;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }
    public static void beginMenu(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void beginMenuItem(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {}

    public static void collapsingHeaderExitable(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        CollapsingHeaderExitable drawable = (CollapsingHeaderExitable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            DataFieldMapper<NativeBool> mapper = getDataFieldMapperBool(field, drawable);

            mapper.setNativeFromField();
            boolean isOpen = imGui.collapsingHeader(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
            if(isOpen){
                jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void collapsingHeader(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        CollapsingHeader drawable = (CollapsingHeader) drawable0;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
        }
    }

    public static void treeNodeEx(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        TreeNodeEx drawable = (TreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.treePop();
        }
    }

    public static void colorText(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        ColorText drawable = (ColorText) drawable0;
        Vec4 color = drawable.color;
        JImVec4 jImVec4 = cachedjimVec.computeIfAbsent(color.hashCode(), value -> JaweStaticDeallocateManager.createJImVec4(color.x, color.y, color.z, color.w));
        imGui.textColored(jImVec4, drawable.text);
    }


    public static void text(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        Text drawable = (Text) drawable0;
        imGui.text(drawable.text);
    }

    public static void selectable(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        Selectable drawable = (Selectable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("selected");
            DataFieldMapper<NativeBool> mapper = getDataFieldMapperBool(field, drawable);
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

    public static void sameLine(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer){
        SameLine drawable = (SameLine) drawable0;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void newLine(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        imGui.newLine();
    }

    public static void jaweDrawableProcess(JImGui imGui, Drawables drawable, ClazzDeserializer<JImGui> parentDrawer) {
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void jaweDrawableProcess(JImGui imGui, List<Object> drawables, Queue<Object> addWindowQueue, Queue<Object> removeWindowQueue, ClazzDeserializer<JImGui> parentDrawer) {
        for (Object element; (element = addWindowQueue.poll()) != null;){
            drawables.add(element);
        }

        for (Object element; (element = removeWindowQueue.poll()) != null;){
            drawables.remove(element);
        }

        drawables.forEach((nestedDrawable) -> parentDrawer.draw(imGui, nestedDrawable, parentDrawer));
    }

    public static void jaweDrawables(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        Drawables drawable = (Drawables) drawable0;
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void dummy(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        Dummy drawable = (Dummy) drawable0;
        imGui.dummy(drawable.width, drawable.height);
    }

    public static void invisibleButton(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        InvisibleButton drawable = (InvisibleButton) drawable0;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void smallButton(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        SmallButton drawable = (SmallButton) drawable0;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void spacing(JImGui imGui, Object drawable0, ClazzDeserializer<JImGui> parentDrawer) {
        imGui.spacing();
    }

}
