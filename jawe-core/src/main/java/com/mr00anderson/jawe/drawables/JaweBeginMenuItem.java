package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

public class JaweBeginMenuItem implements JaweDrawable {

    public String label;
    public boolean enabled;
    public ActivationHandler<JaweBeginMenuItem> activationHandler = imGuiDrawable -> {};

    @Override
    public void draw(JImGui imGui) {
        if(imGui.beginMenu(label, enabled) && activationHandler != null) activationHandler.handle(this);
    }
}
