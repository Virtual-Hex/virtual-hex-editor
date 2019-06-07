package com.virtual_hex.data;

public class Selectable<T extends Selectable> extends Label {


    public boolean selected;
    public int flags;
    public int width;
    public int height;

    public Selectable() {
    }

    public Selectable(boolean selected, int flags, int width, int height) {
        this.selected = selected;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }

    public Selectable(String label, boolean selected, int flags, int width, int height) {
        super(label);
        this.selected = selected;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }
}
