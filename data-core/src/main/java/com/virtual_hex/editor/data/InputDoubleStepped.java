package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L466
 */
public class InputDoubleStepped extends InputDouble {

    public double step;
    public double stepFast;
    public int flags;

    public InputDoubleStepped() {
    }

    public InputDoubleStepped(String label, double step, double stepFast, int flags) {
        super(label);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    public InputDoubleStepped(String label, double value, double step, double stepFast, int flags) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }
}
