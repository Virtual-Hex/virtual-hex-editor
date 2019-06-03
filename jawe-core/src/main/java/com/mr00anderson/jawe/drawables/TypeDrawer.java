package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

@FunctionalInterface
public interface TypeDrawer {

    /**
     *
     * @param imGui JImGui reference
     * @param drawable0 Can be any object
     * @param drawer JaweClazzDrawer this is the parent which was used to call this method, this can be useful
     *               when needing to use already provided methods to draw
     */
    void draw(JImGui imGui, Object drawable0, JaweClazzDrawer drawer);
}
