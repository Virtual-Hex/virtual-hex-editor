package com.virtual_hex.editor.data;

public class ProgressBar extends UIComponent{

    public float fraction;
    public float width;
    public float height;
    public String overlay;

    public ProgressBar() {
    }

    public ProgressBar(float fraction) {
        this.fraction = fraction;
    }

    public ProgressBar(float fraction, String overlay) {
        this.fraction = fraction;
        this.overlay = overlay;
    }

    public ProgressBar(float fraction, float width, float height, String overlay) {
        this.fraction = fraction;
        this.width = width;
        this.height = height;
        this.overlay = overlay;
    }
}
