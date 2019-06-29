package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L462
 */
public class InputIntStepped<LABEL> extends InputInt<LABEL> {

    public int step;
    public int stepFast;
    public int flags;

    public InputIntStepped() {
    }

    public InputIntStepped(LABEL label, int step, int stepFast, int flags) {
        super(label);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    public InputIntStepped(LABEL label, int value, int step, int stepFast, int flags) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }
}
