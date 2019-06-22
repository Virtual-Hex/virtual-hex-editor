package com.virtual_hex.editor.data;

public class LabeledComponents extends UIComponents {

    public String label;

    public LabeledComponents() {
    }

    public LabeledComponents(String label) {
        this.label = label;
    }

    public LabeledComponents(String label, String... components) {
        super(components);
        this.label = label;
    }

    public LabeledComponents(String label, UIComponents... components) {
        super(components);
        this.label = label;
    }

    public LabeledComponents(String label, UIComponent... components) {
        super(components);
        this.label = label;
    }

}
