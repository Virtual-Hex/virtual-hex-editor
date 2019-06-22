package com.virtual_hex.editor.data;

/**
 * Button
 * <p>
 * returning true when pressed
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class Button extends BoxInt {

    public Button() {
    }

    public Button(String label) {
        super(label);
    }

    public Button(String label, int width, int height) {
        super(label, width, height);
    }
}
