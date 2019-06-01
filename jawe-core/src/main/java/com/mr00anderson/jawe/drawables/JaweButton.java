package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class JaweButton {

    public String label;
    public int width;
    public int height;

    /**
     * This will be triggered when selected or deselected
     */
    public ActivationHandler<JaweButton> onActivation = imGuiDrawable -> {};

    public JaweButton() {
    }

    public JaweButton(String label) {
        this.label = label;
    }

    public JaweButton(String label, int width, int height) {
        this.label = label;
        this.width = width;
        this.height = height;
    }

    public JaweButton(String label, ActivationHandler<JaweButton> onActivation) {
        this.label = label;
        this.onActivation = onActivation;
    }

    public JaweButton(String label, int width, int height, ActivationHandler<JaweButton> onActivation) {
        this.label = label;
        this.width = width;
        this.height = height;
        this.onActivation = onActivation;
    }
}
