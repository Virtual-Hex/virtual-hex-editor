package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 *
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L338
 *
 * call between widgets or groups to layout them horizontally. X position given in window coordinates.
 *
 */
public class JaweSameLine implements JaweDrawable {

    public float positionX;
    public float spacingWidth = -1;

    @Override
    public void draw(JImGui imGui) {
        imGui.sameLine(positionX, spacingWidth);
    }

}
