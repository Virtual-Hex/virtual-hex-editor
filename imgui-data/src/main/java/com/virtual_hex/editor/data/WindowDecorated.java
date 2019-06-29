package com.virtual_hex.editor.data;

// TODO New class, in extensions for window scaling
public class WindowDecorated<LABEL> extends Openable<LABEL> {

    public int flags;

    public WindowDecorated() {
    }

    public WindowDecorated(LABEL label, boolean open, int flags) {
        super(label, open);
        this.flags = flags;
    }

    public WindowDecorated(LABEL label, boolean open, int flags, String... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public WindowDecorated(LABEL label, boolean open, int flags, UIComponents... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public WindowDecorated(LABEL label, boolean open, int flags, UIComponent... components) {
        super(label, open, components);
        this.flags = flags;
    }
}
