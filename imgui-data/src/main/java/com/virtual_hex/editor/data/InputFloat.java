package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L458
 */
public class InputFloat extends Label {

    public float value;

    public InputFloat() {
    }

    public InputFloat(String label) {
        super(label);
    }

    public InputFloat(String label, float value) {
        super(label);
        this.value = value;
    }
}
