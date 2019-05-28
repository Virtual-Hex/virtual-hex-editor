package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweBeginMenu implements JaweDrawable {

    public String label;
    public boolean enabled;
    public ActivationHandler<JaweBeginMenu> onActivation;

    @Override
    public void draw(JImGui imGui, World world) {
        // Returns true on activation
        if(imGui.beginMenu(label, enabled)){

        }
    }
}
