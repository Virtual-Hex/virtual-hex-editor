package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

public class JaweNextColumn implements JaweDrawable {

    @Override
    public void draw(JImGui imGui) {
        imGui.nextColumn();
    }
}
