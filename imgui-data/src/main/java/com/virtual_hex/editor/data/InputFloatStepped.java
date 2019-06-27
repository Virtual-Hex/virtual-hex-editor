package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L458
 */
public class InputFloatStepped extends InputFloat {

    public float step;
    public float stepFast;
    public int flags;
    public String format;

    public InputFloatStepped() {
    }

    public InputFloatStepped(String label, float step, float stepFast, int flags) {
        super(label);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    public InputFloatStepped(String label, float value, float step, float stepFast, int flags) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }
}
