package com.mr00anderson.jawe.drawers.jimgui;

import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.wrappers.NativeBooleanDataFieldMapper;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;

public class DefaultJaweDrawers {

    // TODO Move this class drawing to instance?
    public static final JaweClazzDrawer clazzDraw = new JaweClazzDrawer();



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

    public static void text(JImGui imGui, Object drawable0){
        JaweText drawable = (JaweText) drawable0;
        imGui.text(drawable.text);
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

    public static void jaweOrderedDrawables(JImGui imGui, Object drawable0) {
        JaweDrawables drawable = (JaweDrawables) drawable0;

        for (Object element; (element = drawable.addWindowQueue.poll()) != null;){
            drawable.drawables.add(element);
        }

        for (Object element; (element = drawable.removeWindowQueue.poll()) != null;){
            drawable.drawables.remove(element);
        }

        drawable.drawables.forEach((d) -> clazzDraw.draw(imGui, d));
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
