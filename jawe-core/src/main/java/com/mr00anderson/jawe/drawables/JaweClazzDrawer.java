package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweColumnSet;
import com.mr00anderson.jawe.JaweColumnSetBody;
import com.mr00anderson.jawe.JaweColumnSetHeader;
import com.mr00anderson.jawe.JaweColumnSetRow;
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
public class JaweClazzDrawer {

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JaweClazzDrawer.class);


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

    public Map<Class<?>, TypeDrawer> typeDrawers;
    public String name;


    public JaweClazzDrawer() {
        this.name = "";
        this.typeDrawers = new HashMap<>();
    }

    public JaweClazzDrawer(boolean useDefaultDrawer, String name) {
        this.typeDrawers = new HashMap<>();
        if(useDefaultDrawer){
            init();
        } else {
        }
        this.name = name;
    }

    /**
     *
     * @return a new JaweClazzDrawer with the same typeDrawers as the parents
     */
    public JaweClazzDrawer newFromParent(){
        JaweClazzDrawer clazzDrawer = new JaweClazzDrawer();
        clazzDrawer.typeDrawers.putAll(this.typeDrawers);
        return clazzDrawer;
    }

    private void init() {
        typeDrawers.put(JaweBeginMenu.class, JaweClazzDrawer::beginMenu);

        typeDrawers.put(JaweTabBar.class, JaweClazzDrawer::beginTabBar);
        typeDrawers.put(JaweBeginTabItem.class, JaweClazzDrawer::beginTabItem);
        typeDrawers.put(JaweBeginTabItemExitable.class, JaweClazzDrawer::beginTabItemExitable);
        typeDrawers.put(JaweButton.class, JaweClazzDrawer::button);
        typeDrawers.put(JaweCheckBox.class, JaweClazzDrawer::checkbox);
        typeDrawers.put(JaweCollapsingHeader.class, JaweClazzDrawer::collapsingHeader);
        typeDrawers.put(JaweColorText.class, JaweClazzDrawer::colorText);
        typeDrawers.put(JaweColumns.class, JaweClazzDrawer::columns);
        typeDrawers.put(JaweDummy.class, JaweClazzDrawer::dummy);
        // TODO COMBO
        typeDrawers.put(JaweInputDouble.class, JaweClazzDrawer::inputDouble);
        typeDrawers.put(JaweInputDoubleStepped.class, JaweClazzDrawer::inputDoubleStepped);
        typeDrawers.put(JaweInputFloat.class, JaweClazzDrawer::inputFloat);
        typeDrawers.put(JaweInputFloatStepped.class, JaweClazzDrawer::inputFloatStepped);
        typeDrawers.put(JaweInputInt.class, JaweClazzDrawer::inputInt);
        typeDrawers.put(JaweInputIntStepped.class, JaweClazzDrawer::inputIntStepped);

        typeDrawers.put(JaweInvisibleButton.class, JaweClazzDrawer::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawers.put(JaweNewLine.class, JaweClazzDrawer::newLine);
        typeDrawers.put(JaweNextColumn.class, JaweClazzDrawer::nextColumn);
        // TODO OPEN POPUP
        typeDrawers.put(JaweSameLine.class, JaweClazzDrawer::sameLine);
        typeDrawers.put(JaweSelectable.class, JaweClazzDrawer::selectable);
        typeDrawers.put(JaweSeparator.class, JaweClazzDrawer::seperator);
        typeDrawers.put(JaweSmallButton.class, JaweClazzDrawer::smallButton);
        typeDrawers.put(JaweSpacing.class, JaweClazzDrawer::spacing);
        typeDrawers.put(JaweText.class, JaweClazzDrawer::text);
        typeDrawers.put(JaweTextInput.class, JaweClazzDrawer::inputText);
        typeDrawers.put(JaweTreeNodeEx.class, JaweClazzDrawer::treeNodeEx);
        typeDrawers.put(JaweWindow.class, JaweClazzDrawer::window);

        // This will stay because its needed to structured the drawing
        typeDrawers.put(JaweDrawables.class, JaweClazzDrawer::jaweDrawables);

        typeDrawers.put(ArrayList.class, JaweClazzDrawer::list);
        typeDrawers.put(LinkedList.class, JaweClazzDrawer::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawers.put(List.class, JaweClazzDrawer::list);



        // Editable
    }

    // TODO Register method

    public void drawReflectiveInputColumns(JImGui imGui, Object drawable, JaweClazzDrawer parentDrawer){
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

    public void draw(JImGui imGui, Object drawable, JaweClazzDrawer parentDrawer) {
        Class<?> aClass = drawable.getClass();
        TypeDrawer objectDrawer = typeDrawers.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
//        LOGGER.debug("Drawing a class {}, type {}", drawable, aClass);
        objectDrawer.draw(imGui, drawable, parentDrawer);
    }

    public static void list(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        List list = (List) drawable0;
        list.forEach(d -> parentDrawer.draw(imGui, d, parentDrawer));
    }

    // Check iterations and sub-typing through testing
    public TypeDrawer checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        TypeDrawer biConsumer = typeDrawers.get(aSubTypeClazz);
        return biConsumer == null ? JaweClazzDrawer::emptyDrawable : biConsumer;
    }

    public static void beginTabBar(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweTabBar drawable = (JaweTabBar) drawable0;
        boolean selected = imGui.beginTabBar(drawable.label, drawable.flags);
        if(selected){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.endTabBar();
        }
    }

    public static void beginTabItem(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweBeginTabItem drawable = (JaweBeginTabItem) drawable0;
        boolean selected = imGui.beginTabItem(drawable.label);
        if(selected){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.endTabItem();
        }
    }

    public static void beginTabItemExitable(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweBeginTabItemExitable drawable = (JaweBeginTabItemExitable) drawable0;
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

    public static void endTabBar(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        imGui.endTabBar();
    }

    public static void endTabItem(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        imGui.endTabItem();
    }

    public static void seperator(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        imGui.separator();
    }


    public static void nextColumn(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        imGui.nextColumn();
    }

    public static void columns(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweColumns drawable = (JaweColumns) drawable0;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public static void window(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweWindow drawable = (JaweWindow) drawable0;
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
    public static void checkbox(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweCheckBox drawable = (JaweCheckBox) drawable0;
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

    public static void inputText(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweTextInput drawable = (JaweTextInput) drawable0;
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

    public static void inputInt(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputInt drawable = (JaweInputInt) drawable0;
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

    public static void inputIntStepped(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputIntStepped drawable = (JaweInputIntStepped) drawable0;
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

    public static void inputFloat(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputFloat drawable = (JaweInputFloat) drawable0;
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

    public static void inputFloatStepped(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputFloatStepped drawable = (JaweInputFloatStepped) drawable0;
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


    public static void inputDouble(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputDouble drawable = (JaweInputDouble) drawable0;
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

    public static void inputDoubleStepped(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweInputFloatStepped drawable = (JaweInputFloatStepped) drawable0;
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

    public static void button(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweButton drawable = (JaweButton) drawable0;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }
    public static void beginMenu(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void beginMenuItem(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {}

    public static void collapsingHeaderExitable(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweCollapsingHeaderExitable drawable = (JaweCollapsingHeaderExitable) drawable0;
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

    public static void collapsingHeader(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweCollapsingHeader drawable = (JaweCollapsingHeader) drawable0;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
        }
    }

    public static void treeNodeEx(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            jaweDrawableProcess(imGui, drawable.drawables, parentDrawer);
            imGui.treePop();
        }
    }

    public static void colorText(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweColorText drawable = (JaweColorText) drawable0;
        imGui.textColored(drawable.color, drawable.text);
    }


    public static void text(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweText drawable = (JaweText) drawable0;
        imGui.text(drawable.text);
    }

    public static void selectable(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweSelectable drawable = (JaweSelectable) drawable0;
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

    public static void sameLine(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer){
        JaweSameLine drawable = (JaweSameLine) drawable0;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void newLine(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        imGui.newLine();
    }

    public static void jaweDrawableProcess(JImGui imGui, JaweDrawables drawable, JaweClazzDrawer parentDrawer) {
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void jaweDrawableProcess(JImGui imGui, List<Object> drawables, Queue<Object> addWindowQueue, Queue<Object> removeWindowQueue, JaweClazzDrawer parentDrawer) {
        for (Object element; (element = addWindowQueue.poll()) != null;){
            drawables.add(element);
        }

        for (Object element; (element = removeWindowQueue.poll()) != null;){
            drawables.remove(element);
        }

        drawables.forEach((nestedDrawable) -> parentDrawer.draw(imGui, nestedDrawable, parentDrawer));
    }

    public static void jaweDrawables(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweDrawables drawable = (JaweDrawables) drawable0;
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue, parentDrawer);
    }

    public static void dummy(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweDummy drawable = (JaweDummy) drawable0;
        imGui.dummy(drawable.width, drawable.height);
    }

    public static void invisibleButton(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweInvisibleButton drawable = (JaweInvisibleButton) drawable0;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void smallButton(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        JaweSmallButton drawable = (JaweSmallButton) drawable0;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable, parentDrawer);
        }
    }

    public static void spacing(JImGui imGui, Object drawable0, JaweClazzDrawer parentDrawer) {
        imGui.spacing();
    }
}
