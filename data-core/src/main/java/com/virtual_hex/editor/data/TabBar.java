package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L577
 */
public class TabBar extends Openable {

    public TabBar() {
    }

    public TabBar(String label, boolean open) {
        super(label, open);
    }

    public TabBar(String label, boolean open, String... components) {
        super(label, open, components);
    }

    public TabBar(String label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public TabBar(String label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
