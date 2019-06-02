package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweJImGui;
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

    public Map<Class<?>, BiConsumer<JImGui, Object>> typeDrawer;
    public String name;
    public Object baseDrawable;

    public JaweClazzDrawer() {
        this.name = "";
        this.typeDrawer = new HashMap<>();
    }

    public JaweClazzDrawer(boolean useDefaultDrawer, String name, Object baseDrawable) {
        this.typeDrawer = new HashMap<>();
        if(useDefaultDrawer){
            init();
        } else {
        }
        this.name = name;
        this.baseDrawable = baseDrawable;
    }

    // Field cache maybe, have to check the new instantiation of natives every time
    // Could use native pool

    // TODO Native types need to be cached and not recreated everytime

    private void init() {
        typeDrawer.put(JaweBeginMenu.class, this::beginMenu);
        typeDrawer.put(JaweBeginMenuItem.class, this::beginMenuItem);
        typeDrawer.put(JaweTabBar.class, this::beginTabBar);
        typeDrawer.put(JaweBeginTabItem.class, this::beginTabItem);
        typeDrawer.put(JaweButton.class, this::button);
        typeDrawer.put(JaweCheckBox.class, this::checkbox);
        typeDrawer.put(JaweCollapsingHeader.class, this::collapsingHeader);
        typeDrawer.put(JaweColorText.class, this::colorText);
        typeDrawer.put(JaweColumns.class, this::columns);
        // TODO COMBO
        typeDrawer.put(JaweDummy.class, this::dummy);
        typeDrawer.put(JaweEndTabBar.class, this::beginTabBar);
        typeDrawer.put(JaweEndTabItem.class, this::beginTabItem);
        typeDrawer.put(JaweInvisibleButton.class, this::invisibleButton);
        // TODO MENU
        // TODO MENU ITEM
        typeDrawer.put(JaweNewLine.class, this::newLine);
        typeDrawer.put(JaweNextColumn.class, this::nextColumn);
        // TODO OPEN POPUP
        typeDrawer.put(JaweSameLine.class, this::sameLine);
        typeDrawer.put(JaweSelectable.class, this::selectable);
        typeDrawer.put(JaweSeparator.class, this::seperator);
        typeDrawer.put(JaweSmallButton.class, this::smallButton);
        typeDrawer.put(JaweSpacing.class, this::spacing);
        typeDrawer.put(JaweText.class, this::text);
        typeDrawer.put(JaweTreeNodeEx.class, this::treeNodeEx);
        typeDrawer.put(JaweTreeNodeExNoPop.class, this::treeNodeExNoPop);
        typeDrawer.put(JaweWindow.class, this::window);

        // This will stay because its needed to structured the drawing
        typeDrawer.put(JaweDrawables.class, this::jaweDrawables);

        typeDrawer.put(ArrayList.class, this::list);
        typeDrawer.put(LinkedList.class, this::list);

        // Todo determine way to get subtypes, look at serialization libs, so we dont have to register everything
        // when we can just register a subtype

        typeDrawer.put(List.class, this::list);
    }


    // TODO Register method

    // External
    public void draw(JImGui imGui) {
        Class<?> aClass = baseDrawable.getClass();
        BiConsumer<JImGui, Object> objectDrawer = typeDrawer.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
        objectDrawer.accept(imGui, baseDrawable);
    }

    protected void draw(JImGui imGui, Object nestedDrawable) {
        Class<?> aClass = nestedDrawable.getClass();
        BiConsumer<JImGui, Object> objectDrawer = typeDrawer.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
        objectDrawer.accept(imGui, nestedDrawable);
    }

    public void list(JImGui imGui, Object drawable0) {
        List list = (List) drawable0;
        list.forEach(d -> draw(imGui, d));
    }

    // Check iterations and sub-typing through testing
    public BiConsumer<JImGui, Object> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        BiConsumer<JImGui, Object> biConsumer = typeDrawer.get(aSubTypeClazz);
        return biConsumer == null ? this::emptyDrawable : biConsumer;
    }

    public void beginTabBar(JImGui imGui, Object drawable0){
        JaweTabBar drawable = (JaweTabBar) drawable0;
        boolean selected = imGui.beginTabBar(drawable.label, drawable.flags);
        if(selected){
            jaweDrawableProcess(imGui, drawable.drawables);
            imGui.endTabBar();
        }
    }

    public void beginTabItem(JImGui imGui, Object drawable0){
        JaweBeginTabItem drawable = (JaweBeginTabItem) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            mapper.setNativeFromField();
            boolean selected = imGui.beginTabItem(drawable.label, mapper.getNativeData(), drawable.flags);
            if(selected){
                jaweDrawableProcess(imGui, drawable.drawables);
                imGui.endTabItem();
            }
            // make sure the field is updated at the end
            mapper.setFieldFromNative();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void endTabBar(JImGui imGui, Object drawable0){
        imGui.endTabBar();
    }

    public void endTabItem(JImGui imGui, Object drawable0){
        imGui.endTabItem();
    }

    public void seperator(JImGui imGui, Object drawable0) {
        imGui.separator();
    }


    public void nextColumn(JImGui imGui, Object drawable0){
        imGui.nextColumn();
    }

    public void columns(JImGui imGui, Object drawable0){
        JaweColumns drawable = (JaweColumns) drawable0;
        imGui.columns(drawable.count, drawable.stringId, drawable.border);
    }

    public void window(JImGui imGui, Object drawable0) {
        JaweWindow drawable = (JaweWindow) drawable0;

        Field field;
        try {
            field = drawable.getClass().getField("open");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            mapper.setNativeFromField();
            if(imGui.begin(drawable.label, mapper.getNativeData(), drawable.flags)) {
                mapper.setFieldFromNative();
                jaweDrawableProcess(imGui, drawable.drawables);
                drawable.onActivation.handle(drawable);
            }
            imGui.end();// May need to be moved up into the if loop
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    // This wraps a field mapper
    public void checkbox(JImGui imGui, Object drawable0) {
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

    public void button(JImGui imGui, Object drawable0) {
        JaweButton drawable = (JaweButton) drawable0;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable);
        }
    }
    public void beginMenu(JImGui imGui, Object drawable0) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public void beginMenuItem(JImGui imGui, Object drawable0) {
        //TODO
    }

    public void emptyDrawable(JImGui imGui, Object drawable0) {}

    public void collapsingHeaderExitable(JImGui imGui, Object drawable0) {
        JaweCollapsingHeaderExitable drawable = (JaweCollapsingHeaderExitable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("open");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);

            mapper.setNativeFromField();
            boolean isOpen = imGui.collapsingHeader(drawable.label, mapper.getNativeData(), drawable.flags);
            mapper.setFieldFromNative();
            if(isOpen){
                jaweDrawableProcess(imGui, drawable.drawables);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public void collapsingHeader(JImGui imGui, Object drawable0) {
        JaweCollapsingHeader drawable = (JaweCollapsingHeader) drawable0;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            jaweDrawableProcess(imGui, drawable.drawables);
        }
    }

    public void treeNodeEx(JImGui imGui, Object drawable0) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            jaweDrawableProcess(imGui, drawable.drawables);
            imGui.treePop();
        }
    }

    public void treeNodeExNoPop(JImGui imGui, Object drawable0) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            jaweDrawableProcess(imGui, drawable.drawables);
        }
    }

    public void colorText(JImGui imGui, Object drawable0){
        JaweColorText drawable = (JaweColorText) drawable0;
        imGui.textColored(drawable.color, drawable.text);
    }

    public transient static final WeakHashMap<String, byte[]> cachedBytes = new WeakHashMap<>();

    public void text(JImGui imGui, Object drawable0){
        JaweText drawable = (JaweText) drawable0;
        byte[] bytes = cachedBytes.computeIfAbsent(drawable.text, s -> s.getBytes(StandardCharsets.UTF_8));
        JaweJImGui jaweJImGui = (JaweJImGui) imGui;
        jaweJImGui.text(bytes);
    }

    public void selectable(JImGui imGui, Object drawable0){
        JaweSelectable drawable = (JaweSelectable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("selected");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            //  returning the state true when open or false when unselected
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

    public void sameLine(JImGui imGui, Object drawable0){
        JaweSameLine drawable = (JaweSameLine) drawable0;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public void newLine(JImGui imGui, Object drawable0) {
        imGui.newLine();
    }

    public void jaweDrawableProcess(JImGui imGui, JaweDrawables drawable) {
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue);
    }

    public void jaweDrawableProcess(JImGui imGui, List<Object> drawables, Queue<Object> addWindowQueue, Queue<Object> removeWindowQueue) {
        for (Object element; (element = addWindowQueue.poll()) != null;){
            drawables.add(element);
        }

        for (Object element; (element = removeWindowQueue.poll()) != null;){
            drawables.remove(element);
        }

        drawables.forEach((nestedDrawable) -> draw(imGui, nestedDrawable));
    }

    public void jaweDrawables(JImGui imGui, Object drawable0) {
        JaweDrawables drawable = (JaweDrawables) drawable0;
        jaweDrawableProcess(imGui, drawable.drawables, drawable.addWindowQueue, drawable.removeWindowQueue);
    }

    public void dummy(JImGui imGui, Object drawable0) {
        JaweDummy drawable = (JaweDummy) drawable0;
        imGui.dummy(drawable.width, drawable.height);
    }

    public void invisibleButton(JImGui imGui, Object drawable0) {
        JaweInvisibleButton drawable = (JaweInvisibleButton) drawable0;
        if(imGui.invisibleButton(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable);
        }
    }

    public void smallButton(JImGui imGui, Object drawable0) {
        JaweSmallButton drawable = (JaweSmallButton) drawable0;
        if(imGui.smallButton(drawable.label)){
            drawable.onActivation.handle(drawable);
        }
    }

    public void spacing(JImGui imGui, Object drawable0) {
        imGui.spacing();
    }
}
