package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImVec4;

/**
 *  shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class JaweColorText extends JaweDrawableText {

    public JImVec4 color;

    public JaweColorText() {
    }

    public JaweColorText(String text) {
        super(text);
    }

    public JaweColorText(JImVec4 color) {
        this.color = color;
    }

    public JaweColorText(String text, JImVec4 color) {
        super(text);
        this.color = color;
    }
}
