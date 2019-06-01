package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * This should represent the smallest unit translated to JImGui
 * This should map to JImGui#methodNameHere when created
 */
@FunctionalInterface
public interface JaweDrawable<T extends JaweDrawable> {
    /**
     * Used to draw specific types
     *
     * @param imGui
     * @param drawable
     */
    void draw(JImGui imGui, T drawable);
}
