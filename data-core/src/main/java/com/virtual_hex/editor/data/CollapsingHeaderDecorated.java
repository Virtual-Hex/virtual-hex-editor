package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L498
 */
public class CollapsingHeaderDecorated extends TreeNodeEx {

    public CollapsingHeaderDecorated() {
    }

    public CollapsingHeaderDecorated(String label, boolean open, int flags) {
        super(label, open, flags);
    }

    public CollapsingHeaderDecorated(String label, boolean open, int flags, String... components) {
        super(label, open, flags, components);
    }

    public CollapsingHeaderDecorated(String label, boolean open, int flags, UIComponents... components) {
        super(label, open, flags, components);
    }

    public CollapsingHeaderDecorated(String label, boolean open, int flags, UIComponent... components) {
        super(label, open, flags, components);
    }
}
