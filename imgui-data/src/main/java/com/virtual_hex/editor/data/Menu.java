package com.virtual_hex.editor.data;


/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L533
 */
public class Menu<LABEL> extends LabeledComponents<LABEL> {

    public boolean enabled;

    public Menu() {
    }

    public Menu(LABEL label) {
        super(label);
        this.enabled = true;
    }

    public Menu(LABEL label, UIComponents... components) {
        super(label, components);
        this.enabled = true;
    }

    public Menu(LABEL label, UIComponent... components) {
        super(label, components);
        this.enabled = true;
    }

    public Menu(LABEL label, boolean enabled) {
        super(label);
        this.enabled = enabled;
    }

    public Menu(LABEL label, boolean enabled, UIComponents... components) {
        super(label, components);
        this.enabled = enabled;
    }

    public Menu(LABEL label, boolean enabled, UIComponent... components) {
        super(label, components);
        this.enabled = enabled;
    }
}