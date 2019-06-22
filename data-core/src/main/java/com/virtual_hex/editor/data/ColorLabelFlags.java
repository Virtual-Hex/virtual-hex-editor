package com.virtual_hex.editor.data;

public class ColorLabelFlags extends Label {

    public Vec4 color;
    public int flags;

    public ColorLabelFlags() {
    }

    public ColorLabelFlags(String label, Vec4 color) {
        super(label);
        this.color = color;
    }

    public ColorLabelFlags(String label, Vec4 color, int flags) {
        super(label);
        this.color = color;
        this.flags = flags;
    }
}
