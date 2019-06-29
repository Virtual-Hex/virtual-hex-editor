package com.virtual_hex.editor.data;

/**
 * Button
 * <p>
 * returning true when pressed
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class ColorButton<LABEL> extends BoxInt<LABEL> {

    public int flags;
    public Vec4 color;

    public ColorButton() {
    }

    public ColorButton(LABEL label, Vec4 color) {
        super(label);
        this.color = color;
    }

    public ColorButton(LABEL label, int width, int height, Vec4 color) {
        super(label, width, height);
        this.color = color;
    }

    public ColorButton(LABEL label, int flags, Vec4 color) {
        super(label);
        this.flags = flags;
        this.color = color;
    }

    public ColorButton(LABEL label, int width, int height, int flags, Vec4 color) {
        super(label, width, height);
        this.flags = flags;
        this.color = color;
    }
}
