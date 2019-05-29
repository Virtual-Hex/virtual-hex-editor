package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 *
 *   button behavior without the visuals, frequently useful to build custom behaviors using the public api (along with IsItemActive, IsItemHovered, etc.)
 */
public class JaweInvisibleButton implements JaweDrawable {

    public String label;
    public int width;
    public int height;

    /**
     * This will be triggered when selected or deselected
     */
    public ActivationHandler<JaweInvisibleButton> onActivation = imGuiDrawable -> {};

    public JaweInvisibleButton() {
    }

    public JaweInvisibleButton(String label) {
        this.label = label;
    }

    @Override
    public void draw(JImGui imGui, World world) {
        //
        if(imGui.invisibleButton(label, width, height)){
            onActivation.handle(this);
        }
    }
}
