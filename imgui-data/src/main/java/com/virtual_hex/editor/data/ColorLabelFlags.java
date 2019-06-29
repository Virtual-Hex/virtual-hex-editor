package com.virtual_hex.editor.data;

public class ColorLabelFlags<LABEL, COLOR> extends Label<LABEL> {

    public COLOR color;
    public int flags;

    public ColorLabelFlags() {
    }

    public ColorLabelFlags(LABEL label, COLOR color) {
        super(label);
        this.color = color;
    }

    public ColorLabelFlags(LABEL label, COLOR color, int flags) {
        super(label);
        this.color = color;
        this.flags = flags;
    }
}
