package com.virtual_hex.editor.data;

public class Openable extends LabeledComponents {

    /**
     * A native boolean which will be converted to a java boolean before worldWrapper serialization
     */
    public boolean open;

    public Openable() {
    }

    public Openable(String label, boolean open) {
        super(label);
        this.open = open;
    }

    public Openable(String label, boolean open, String... components) {
        super(label, components);
        this.open = open;
    }

    public Openable(String label, boolean open, UIComponents... components) {
        super(label, components);
        this.open = open;
    }

    public Openable(String label, boolean open, UIComponent... components) {
        super(label, components);
        this.open = open;
    }
}
