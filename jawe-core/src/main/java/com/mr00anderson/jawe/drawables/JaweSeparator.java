package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

public class JaweSeparator implements JaweDrawable {

    @Override
    public void draw(JImGui imGui) {
        imGui.separator();
    }
}
