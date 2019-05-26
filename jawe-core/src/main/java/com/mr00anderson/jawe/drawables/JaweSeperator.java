package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweSeperator extends AbstractJaweDrawable {

    @Override
    public void draw(JImGui imGui, World world) {
        JImGui.separator();
    }

    @Override
    public void dispose() {

    }
}
