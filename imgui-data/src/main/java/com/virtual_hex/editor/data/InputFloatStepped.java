package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L458
 */
public class InputFloatStepped<LABEL> extends InputFloat<LABEL> {

    public float step;
    public float stepFast;
    public int flags;
    public LABEL format;

    public InputFloatStepped() {
    }

    public InputFloatStepped(LABEL label, float step, float stepFast, int flags) {
        super(label);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    public InputFloatStepped(LABEL label, float value, float step, float stepFast, int flags) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    public InputFloatStepped(LABEL label, float step, float stepFast, int flags, LABEL format) {
        super(label);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
        this.format = format;
    }

    public InputFloatStepped(LABEL label, float value, float step, float stepFast, int flags, LABEL format) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
        this.format = format;
    }
}
