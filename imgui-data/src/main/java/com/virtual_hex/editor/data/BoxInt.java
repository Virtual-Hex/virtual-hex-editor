package com.virtual_hex.editor.data;

public class BoxInt extends Label {

    public int width;
    public int height;

    public BoxInt() {
    }

    public BoxInt(String label) {
        super(label);
    }

    public BoxInt(String label, int width, int height) {
        super(label);
        this.width = width;
        this.height = height;
    }
}
