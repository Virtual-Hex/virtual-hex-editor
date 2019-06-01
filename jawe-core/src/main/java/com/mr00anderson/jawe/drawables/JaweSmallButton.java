package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

/**
 * button with FramePadding=(0,0) to easily embed within text
 *
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L395
 *
 * returning true when pressed and triggers activation handler
 *
 */
public class JaweSmallButton   {

    public String label;

    /**
     * This will be triggered when selected or deselected
     */
    public ActivationHandler<JaweSmallButton> onActivation = imGuiDrawable -> {};

    public JaweSmallButton() {
    }

    public JaweSmallButton(String label) {
        this.label = label;
    }


    public void draw(JImGui imGui) {
        if(imGui.smallButton(label)){
            onActivation.handle(this);
        }
    }
}
