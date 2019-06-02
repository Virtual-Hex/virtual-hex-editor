package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;

public class JaweSelectable {

    public String label;
    public boolean selected;
    public int flags;
    public int width;
    public int height;

    /**
     * This will be triggered when open or deselected
     */
    public ActivationHandler<JaweSelectable> onActivation = imGuiDrawable -> {};

    public JaweSelectable() {
    }

    public JaweSelectable(String label) {
        this.label = label;
    }

    public JaweSelectable(String label, int flags) {
        this.label = label;
        this.flags = flags;
    }

    public JaweSelectable(String label, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.onActivation = onActivation;
    }

    public JaweSelectable(String label, int flags, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.flags = flags;
        this.onActivation = onActivation;
    }

    public JaweSelectable(String label, boolean selected, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.selected = selected;
        this.onActivation = onActivation;
    }

    public JaweSelectable(String label, boolean selected, int flags, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.selected = selected;
        this.flags = flags;
        this.onActivation = onActivation;
    }
}
