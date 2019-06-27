package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L497
 */
public class CollapsingHeader extends TreeNode {

    public CollapsingHeader() {
    }

    public CollapsingHeader(String label, boolean open) {
        super(label, open);
    }

    public CollapsingHeader(String label, boolean open, String... components) {
        super(label, open, components);
    }

    public CollapsingHeader(String label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public CollapsingHeader(String label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
