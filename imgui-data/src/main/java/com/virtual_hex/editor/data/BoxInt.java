package com.virtual_hex.editor.data;

public class BoxInt<LABEL> extends Label<LABEL> {

    public int width;
    public int height;

    public BoxInt() {
    }

    public BoxInt(LABEL label) {
        super(label);
    }

    public BoxInt(LABEL label, int width, int height) {
        super(label);
        this.width = width;
        this.height = height;
    }
}
