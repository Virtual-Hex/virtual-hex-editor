package com.virtual_hex.editor.data;

public class LabeledComponents<LABEL> extends UIComponents {

    public LABEL label;

    public LabeledComponents() {
    }

    public LabeledComponents(LABEL label) {
        this.label = label;
    }


    public LabeledComponents(LABEL label, UIComponents... components) {
        super(components);
        this.label = label;
    }

    public LabeledComponents(LABEL label, UIComponent... components) {
        super(components);
        this.label = label;
    }

}
