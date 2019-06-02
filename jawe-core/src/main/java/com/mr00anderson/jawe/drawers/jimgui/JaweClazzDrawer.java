package com.mr00anderson.jawe.drawers.jimgui;

import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.wrappers.NativeBooleanDataFieldMapper;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiConsumer;


/**
 * Depends on JImGui // Could be extracted out one layer and friendly to any backend in the future
 */
public class JaweClazzDrawer {

    protected Map<Class<?>, BiConsumer<JImGui, Object>> typeDrawer = new HashMap<>();
    // TODO Type and Field Type Drawer

    // Serialization can be on a class bases for drawing with this, but then alot of new refernces, similar to the static final issue
    public JaweClazzDrawer() {
        init();
    }

    private void init() {
        typeDrawer.put(JaweBeginMenu.class, JaweClazzDrawer::beginMenu);
        typeDrawer.put(JaweBeginMenuItem.class, JaweClazzDrawer::beginMenuItem);
        typeDrawer.put(JaweBeginTabBar.class, JaweClazzDrawer::beginTabBar);
        typeDrawer.put(JaweBeginTabItem.class, JaweClazzDrawer::beginTabItem);
        typeDrawer.put(JaweButton.class, JaweClazzDrawer::button);
        typeDrawer.put(JaweCheckBox.class, JaweClazzDrawer::checkbox);
        typeDrawer.put(JaweCollapsingHeader.class, JaweClazzDrawer::collapsingHeader);
        typeDrawer.put(JaweColorText.class, JaweClazzDrawer::colorText);
        typeDrawer.put(JaweColumns.class, JaweClazzDrawer::columns);
        // TODO COMBO
        typeDrawer.put(JaweDummy.class, JaweClazzDrawer::dummy);
        typeDrawer.put(JaweEndTabBar.class, JaweClazzDrawer::beginTabBar);
        typeDrawer.put(JaweEndTabItem.class, JaweClazzDrawer::beginTabItem);
        typeDrawer.put(JaweInvisibleButton.class, JaweClazzDrawer::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawer.put(JaweNewLine.class, JaweClazzDrawer::newLine);
        typeDrawer.put(JaweNextColumn.class, JaweClazzDrawer::nextColumn);
        // TODO OPEN POPUP
        typeDrawer.put(JaweSameLine.class, JaweClazzDrawer::sameLine);
        typeDrawer.put(JaweSelectable.class, JaweClazzDrawer::selectable);
        typeDrawer.put(JaweSeparator.class, JaweClazzDrawer::seperator);
        typeDrawer.put(JaweSmallButton.class, JaweClazzDrawer::smallButton);
        typeDrawer.put(JaweSpacing.class, JaweClazzDrawer::spacing);
        typeDrawer.put(JaweText.class, JaweClazzDrawer::text);
        typeDrawer.put(JaweTreeNodeEx.class, JaweClazzDrawer::treeNodeEx);
        typeDrawer.put(JaweTreeNodeExNoPop.class, JaweClazzDrawer::treeNodeExNoPop);
        typeDrawer.put(JaweWindow.class, JaweClazzDrawer::window);

        // This will stay because its needed to structured the drawing
        typeDrawer.put(JaweDrawables.class, JaweClazzDrawer::jaweOrderedDrawables);

        typeDrawer.put(ArrayList.class, this::list);
        typeDrawer.put(LinkedList.class, this::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawer.put(List.class, this::list);
    }


    public void draw(JImGui imGui, Object objectToDraw) {
        Class<?> aClass = objectToDraw.getClass();
        BiConsumer<JImGui, Object> objectDrawer = typeDrawer.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
        objectDrawer.accept(imGui, objectToDraw);
    }

    public void list(JImGui imGui, Object drawable0) {
        List list = (List) drawable0;
        list.forEach(d -> draw(imGui, d));
    }

    // Check iterations and sub-typing through testing
    public BiConsumer<JImGui, Object> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        BiConsumer<JImGui, Object> biConsumer = typeDrawer.get(aSubTypeClazz);
        return biConsumer == null ? JaweClazzDrawer::emptyDrawable : biConsumer;
    }


    // TODO Move this class drawing to instance?
    public static final JaweClazzDrawer clazzDraw = new JaweClazzDrawer();


    public static void beginTabBar(JImGui imGui, Object drawable0){
        JaweBeginTabBar drawable = (JaweBeginTabBar) drawable0;
        boolean selected = imGui.beginTabBar(drawable.label, drawable.flags);
        if(selected){
            jaweOrderedDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue);
            imGui.endTabBar();
        }
    }

    public static void beginTabItem(JImGui imGui, Object drawable0){
        JaweBeginTabItem drawable = (JaweBeginTabItem) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("opened");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            mapper.setNativeFromField();
            boolean selected = imGui.beginTabItem(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
            if(selected){
                jaweOrderedDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue);
                imGui.endTabItem();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void endTabBar(JImGui imGui, Object drawable0){
        imGui.endTabBar();
    }

    public static void endTabItem(JImGui imGui, Object drawable0){
        imGui.endTabItem();
    }

    public static void seperator(JImGui imGui, Object drawable0) {
        imGui.separator();
    }


    public static void nextColumn(JImGui imGui, Object drawable0){
        imGui.nextColumn();
    }

    public static void columns(JImGui imGui, Object drawable0){
        JaweColumns drawable = (JaweColumns) drawable0;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public static void window(JImGui imGui, Object drawable0) {
        JaweWindow drawable = (JaweWindow) drawable0;
        if(imGui.begin(drawable.label, drawable.open, drawable.flags)) {
            drawable.windowContents.forEach(d -> clazzDraw.draw(imGui, d));
            drawable.onActivation.handle(drawable);
        }
        imGui.end();
    }

    // This wraps a field mapper
    public static void checkbox(JImGui imGui, Object drawable0) {
        JaweCheckBox drawable = (JaweCheckBox) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("checked");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            mapper.setNativeFromField();
            imGui.checkbox(drawable.label, mapper.getNativeData());
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void button(JImGui imGui, Object drawable0) {
        JaweButton drawable = (JaweButton) drawable0;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable);
        }
    }
    public static void beginMenu(JImGui imGui, Object drawable0) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void beginMenuItem(JImGui imGui, Object drawable0) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, Object drawable0) {}

    public static void collapsingHeaderExitable(JImGui imGui, Object drawable0) {
        JaweCollapsingHeaderExitable drawable = (JaweCollapsingHeaderExitable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);

            mapper.setNativeFromField();
            boolean isOpen = imGui.collapsingHeader(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
            if(isOpen){
                drawable.drawables.forEach(d -> clazzDraw.draw(imGui, d));
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void collapsingHeader(JImGui imGui, Object drawable0) {
        JaweCollapsingHeader drawable = (JaweCollapsingHeader) drawable0;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            drawable.drawables.forEach(d -> clazzDraw.draw(imGui, d));
        }
    }

    public static void treeNodeEx(JImGui imGui, Object drawable0) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            drawable.drawables.forEach(d -> clazzDraw.draw(imGui, d));
            imGui.treePop();
        }
    }

    public static void treeNodeExNoPop(JImGui imGui, Object drawable0) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            drawable.drawables.forEach(d -> clazzDraw.draw(imGui, d));
        }
    }

    public static void colorText(JImGui imGui, Object drawable0){
        JaweColorText drawable = (JaweColorText) drawable0;
        imGui.textColored(drawable.color, drawable.text);
    }

    public transient static final WeakHashMap<String, byte[]> cachedBytes = new WeakHashMap<>();

    public static void text(JImGui imGui, Object drawable0){
        JaweText drawable = (JaweText) drawable0;
        byte[] bytes = cachedBytes.computeIfAbsent(drawable.text, s -> s.getBytes(StandardCharsets.UTF_8));
        JaweJImGui jaweJImGui = (JaweJImGui) imGui;
        jaweJImGui.text(bytes);
    }

    public static void selectable(JImGui imGui, Object drawable0){
        JaweSelectable drawable = (JaweSelectable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("selected");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            //  returning the state true when selected or false when unselected
            //  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
            mapper.setNativeFromField();
            if(imGui.selectable(drawable.label, mapper.getNativeData(), drawable.flags, drawable.width, drawable.height)){
                mapper.setFieldFromNative();
                drawable.onActivation.handle(drawable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void sameLine(JImGui imGui, Object drawable0){
        JaweSameLine drawable = (JaweSameLine) drawable0;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void newLine(JImGui imGui, Object drawable0) {
        imGui.newLine();
    }

    public static void jaweOrderedDrawableProcess(JImGui imGui, List<Object> drawables, Queue<Object> addWindowQueue, Queue<Object> removeWindowQueue) {
        for (Object element; (element = addWindowQueue.poll()) != null;){
            drawables.add(element);
        }

        for (Object element; (element = removeWindowQueue.poll()) != null;){
            drawables.remove(element);
        }

        drawables.forEach((d) -> clazzDraw.draw(imGui, d));
    }

    public static void jaweOrderedDrawables(JImGui imGui, Object drawable0) {
        JaweDrawables drawable = (JaweDrawables) drawable0;
        jaweOrderedDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue);
    }

    public static void dummy(JImGui imGui, Object drawable0) {
        JaweDummy drawable = (JaweDummy) drawable0;
        imGui.dummy(drawable.width, drawable.height);
    }

    public static void invisibleButton(JImGui imGui, Object drawable0) {
        JaweInvisibleButton drawable = (JaweInvisibleButton) drawable0;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable);
        }
    }

    public static void smallButton(JImGui imGui, Object drawable0) {
        JaweSmallButton drawable = (JaweSmallButton) drawable0;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable);
        }
    }

    public static void spacing(JImGui imGui, Object drawable0) {
        imGui.spacing();
    }
}
