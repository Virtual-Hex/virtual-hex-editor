package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L462
 */
public class InputInt extends Label {

    public int value;

    // TODO INSERT HANDLER FOR VERIFICATION AND FEEDBACK

    public InputInt() {
    }

    public InputInt(String label) {
        super(label);
    }

    public InputInt(String label, int value) {
        super(label);
        this.value = value;
    }
}
