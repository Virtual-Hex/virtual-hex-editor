package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

public class JaweTestingWindow implements JaweDrawable {

    public JaweSelectable[] jaweSelectable = {
            new JaweSelectable("Test Selectable One"),
            new JaweSelectable("Test Selectable Two")
    };


    @Override
    public void draw(JImGui imGui, World world) {
        for (int i = 0; i < jaweSelectable.length; i++) {
            jaweSelectable[i].draw(imGui, world);
        }
    }

}
