package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

/**
 *  shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class JaweColorText extends JaweDrawableText {

    public JImVec4 color;

    @Override
    public void draw(JImGui imGui) {
        imGui.textColored(color, text);
    }
}
