package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * add a dummy item of given size. unlike InvisibleButton(), Dummy() won't take the mouse click or be navigable into.
 */
public class JaweDummy {

    public float width;
    public float height;


    public void draw(JImGui imGui) {
        imGui.dummy(width, height);
    }
}
