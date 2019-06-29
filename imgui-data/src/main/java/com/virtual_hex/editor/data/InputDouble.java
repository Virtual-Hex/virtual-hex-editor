package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L466
 */
public class InputDouble<LABEL> extends Label<LABEL> {

    public double value;

    public InputDouble() {
    }

    public InputDouble(LABEL label) {
        super(label);
    }

    public InputDouble(LABEL label, double value) {
        super(label);
        this.value = value;
    }
}
