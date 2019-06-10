package com.virtual_hex.data;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 *
 *   button behavior without the visuals, frequently useful to build custom behaviors using the public api (along with IsItemActive, IsItemHovered, etc.)
 */
public class InvisibleButton extends Label {

    public int width;
    public int height;

    public InvisibleButton() {
    }

    public InvisibleButton(String label) {
        super(label);
    }

    public InvisibleButton(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public InvisibleButton(String label, int width, int height) {
        super(label);
        this.width = width;
        this.height = height;
    }
}
