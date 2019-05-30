package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * add vertical spacing.
 */
public class JaweSpacing implements JaweDrawable {

    @Override
    public void draw(JImGui imGui) {
        imGui.spacing();
    }
}
