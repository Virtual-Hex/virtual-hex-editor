package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L579
 */
public class TabItem extends OpenableStringLabel {

    public TabItem() {
    }

    public TabItem(String label, boolean open) {
        super(label, open);
    }

    public TabItem(String label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public TabItem(String label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
