package com.virtual_hex.editor.data;

public class TabItemDecorated extends TabItem {

    public int flags;

    public TabItemDecorated() {
    }

    public TabItemDecorated(String label, boolean open, int flags) {
        super(label, open);
        this.flags = flags;
    }

    public TabItemDecorated(String label, boolean open, int flags, String... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TabItemDecorated(String label, boolean open, int flags, UIComponents... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TabItemDecorated(String label, boolean open, int flags, UIComponent... components) {
        super(label, open, components);
        this.flags = flags;
    }
}
