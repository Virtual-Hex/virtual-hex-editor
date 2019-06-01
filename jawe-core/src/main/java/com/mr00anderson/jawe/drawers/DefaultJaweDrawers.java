package com.mr00anderson.jawe.drawers;

import com.mr00anderson.jawe.ClazzDrawer;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.wrappers.NativeBooleanDataFieldMapper;
import org.ice1000.jimgui.JImGui;

import java.lang.reflect.Field;

public class DefaultJaweDrawers {

    // TODO Move this class drawing to instance?
    public static final ClazzDrawer clazzDraw = new ClazzDrawer();

    public static void drawWindow(JImGui imGui, Object drawable0) {
        JaweWindow drawable = (JaweWindow) drawable0;
        if(imGui.begin(drawable.label, drawable.open, drawable.flags)) {
            drawable.windowContents.forEach(d -> clazzDraw.draw(imGui, d));
            drawable.onActivation.handle(drawable);
        }
        imGui.end();
    }

    // This wraps a field mapper
    public static void drawCheckbox(JImGui imGui, Object drawable0) {
        JaweCheckBox drawable = (JaweCheckBox) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("checked");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            imGui.checkbox(drawable.label, mapper.getData());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void drawButton(JImGui imGui, Object drawable0) {
        JaweButton drawable = (JaweButton) drawable0;
        if(imGui.button(drawable.label, drawable.width, drawable.height)){
            drawable.onActivation.handle(drawable);
        }
    }
    public static void drawBeginMenu(JImGui imGui, Object drawable0) {
        // Returns true on activation
//        if(imGui.beginMenu(label, enabled)){
//          TODO
//        }
    }

    public static void drawBeginMenuItem(JImGui imGui, Object drawable0) {
        //TODO
    }

    public static void emptyDrawable(JImGui imGui, Object drawable0) {}

    public static void drawCollapsingHeader(JImGui imGui, Object drawable0) {
        JaweCollapsingHeader drawable = (JaweCollapsingHeader) drawable0;
        boolean isOpen = imGui.collapsingHeader(drawable.label);
        if(isOpen){
            drawable.drawables.forEach(d -> clazzDraw.draw(imGui, d));
        }
    }


    public static void drawTreeNodeEx(JImGui imGui, Object drawable0) {
        JaweTreeNodeEx drawable = (JaweTreeNodeEx) drawable0;
        boolean open = imGui.treeNodeEx(drawable.label, drawable.flags);
        if(open){
            drawable.drawables.forEach(d -> d.draw(imGui, d));
            imGui.treePop();
        }
    }

    public static void drawColorText(JImGui imGui, Object drawable0){
        JaweColorText drawable = (JaweColorText) drawable0;
        imGui.textColored(drawable.color, drawable.text);
    }

    public static void drawTextFromString(JImGui imGui, Object drawable0){
        String drawable = (String) drawable0;
        imGui.text(drawable);
    }

    public static void drawText(JImGui imGui, Object drawable0){
        JaweText drawable = (JaweText) drawable0;
        imGui.text(drawable.text);
    }

    public static void drawSelectable(JImGui imGui, Object drawable0){
        JaweSelectable drawable = (JaweSelectable) drawable0;
        Field field;
        try {
            field = drawable.getClass().getField("selected");
            NativeBooleanDataFieldMapper mapper = new NativeBooleanDataFieldMapper(field, drawable);
            //  returning the state true when selected or false when unselected
            //  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
            if(imGui.selectable(drawable.label, mapper.getData(), drawable.flags, drawable.width, drawable.height)){
                drawable.onActivation.handle(drawable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void drawSameLine(JImGui imGui, Object drawable0){
        JaweSameLine drawable = (JaweSameLine) drawable0;
        imGui.sameLine(drawable.positionX, drawable.spacingWidth);
    }

    public static void drawNewLine(JImGui imGui, Object drawable0) {
        imGui.newLine();
    }
}
