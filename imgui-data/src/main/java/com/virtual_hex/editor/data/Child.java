package com.virtual_hex.editor.data;

public class Child<LABEL> extends LabeledComponents<LABEL> {

    public boolean border;
    public int flags;
    public int width;
    public int height;

    public Child() {
    }

    public Child(LABEL label, boolean border, int flags, int width, int height) {
        super(label);
        this.border = border;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }

    public Child(LABEL label, boolean border, int flags, int width, int height, String... components) {
        super(label, components);
        this.border = border;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }

    public Child(LABEL label, boolean border, int flags, int width, int height, UIComponents... components) {
        super(label, components);
        this.border = border;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }

    public Child(LABEL label, boolean border, int flags, int width, int height, UIComponent... components) {
        super(label, components);
        this.border = border;
        this.flags = flags;
        this.width = width;
        this.height = height;
    }
}
