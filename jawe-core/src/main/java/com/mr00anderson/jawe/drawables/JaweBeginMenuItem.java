package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

public class JaweBeginMenuItem extends AbstractJaweDrawable {

    public String label;
    public boolean enabled;

    @Override
    public void draw(JImGui imGui, World world) {
        draw0(imGui.beginMenu(label, enabled));
    }

    @Override
    public void dispose() {
        // No native or allocated objects to dispose
    }
}
