package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L531
 */
public class MenuBar<LABEL> extends LabeledComponents<LABEL> {

    public boolean enabled;

    public MenuBar() {
    }

    public MenuBar(LABEL label, boolean enabled) {
        super(label);
        this.enabled = enabled;
    }

    public MenuBar(LABEL label, boolean enabled, UIComponents... components) {
        super(label, components);
        this.enabled = enabled;
    }

    public MenuBar(LABEL label, boolean enabled, UIComponent... components) {
        super(label, components);
        this.enabled = enabled;
    }
}
