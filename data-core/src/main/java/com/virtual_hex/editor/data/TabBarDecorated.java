package com.virtual_hex.editor.data;

public class TabBarDecorated extends Openable {

    public int flags;

    public TabBarDecorated(int flags) {
        this.flags = flags;
    }

    public TabBarDecorated(String label, boolean open, int flags) {
        super(label, open);
        this.flags = flags;
    }

    public TabBarDecorated(String label, boolean open, int flags, String... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TabBarDecorated(String label, boolean open, int flags, UIComponents... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TabBarDecorated(String label, boolean open, int flags, UIComponent... components) {
        super(label, open, components);
        this.flags = flags;
    }
}
