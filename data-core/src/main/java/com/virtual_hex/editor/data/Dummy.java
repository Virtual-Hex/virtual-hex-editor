package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L341
 * <p>
 * add a dummy item of given size. unlike InvisibleButton(), Dummy() won't take the mouse click or be navigable into.
 */
public class Dummy extends AbstractUIComponent {

    public float width;
    public float height;

    public Dummy() {
    }

    public Dummy(float width, float height) {
        this.width = width;
        this.height = height;
    }
}
