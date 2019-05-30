package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * simple formatted text
 */
public class JaweText extends JaweDrawableText {

    public JaweText() {
    }

    public JaweText(String text) {
        super(text);
    }

    @Override
    public void draw(JImGui imGui) {
        imGui.text(text);
    }
}
