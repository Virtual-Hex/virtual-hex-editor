package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * Used to wrap up a set of clazz drawers with its base
 */
public class JaweClazzDrawerWrapper {


    /**
     * If the rendering component is active or not
     */
    public boolean active;

    /**
     * Can be a nest of object
     */
    public Object drawable;

    // External
    public void draw(JImGui imGui, JaweClazzDrawer parentDrawer) {
        if(active) parentDrawer.draw(imGui, drawable, parentDrawer);
    }
}
