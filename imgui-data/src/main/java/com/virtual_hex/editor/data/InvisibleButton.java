package com.virtual_hex.editor.data;

/**
 * Button
 * <p>
 * returning true when pressed
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 * <p>
 * button behavior without the visuals, frequently useful to build custom behaviors using the public api (along with IsItemActive, IsItemHovered, etc.)
 */
public class InvisibleButton extends Button {

    public InvisibleButton() {
    }

    public InvisibleButton(String label) {
        super(label);
    }

    public InvisibleButton(String label, int width, int height) {
        super(label, width, height);
    }
}
