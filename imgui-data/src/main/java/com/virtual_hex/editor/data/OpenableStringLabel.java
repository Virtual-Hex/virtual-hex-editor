package com.virtual_hex.editor.data;

public class OpenableStringLabel extends LabeledComponents<String> {

    /**
     * A native boolean which will be converted to a java boolean before worldWrapper serialization
     */
    public boolean open;

    public OpenableStringLabel() {
    }

    public OpenableStringLabel(String label, boolean open) {
        super(label);
        this.open = open;
    }

    public OpenableStringLabel(String label, boolean open, UIComponents... components) {
        super(label, components);
        this.open = open;
    }

    public OpenableStringLabel(String label, boolean open, UIComponent... components) {
        super(label, components);
        this.open = open;
    }
}
