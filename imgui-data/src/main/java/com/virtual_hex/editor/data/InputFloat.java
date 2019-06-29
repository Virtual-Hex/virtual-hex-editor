package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L458
 */
public class InputFloat<LABEL> extends Label<LABEL> {

    public float value;

    public InputFloat() {
    }

    public InputFloat(LABEL label) {
        super(label);
    }

    public InputFloat(LABEL label, float value) {
        super(label);
        this.value = value;
    }
}
