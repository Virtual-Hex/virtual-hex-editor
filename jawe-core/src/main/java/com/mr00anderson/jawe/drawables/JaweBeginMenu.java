package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweBeginMenu extends AbstractJaweDrawable {

    public String label;
    public boolean enabled;

    @Override
    public void draw(JImGui imGui, World world) {
        imGui.beginMenu(label, enabled);
    }

    @Override
    public void dispose() {

    }
}
