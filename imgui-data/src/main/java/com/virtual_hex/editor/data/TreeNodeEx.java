package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L487
 * <p>
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
public class TreeNodeEx<LABEL> extends TreeNode<LABEL> {

    public int flags;

    public TreeNodeEx() {
    }

    public TreeNodeEx(LABEL label, boolean open, int flags) {
        super(label, open);
        this.flags = flags;
    }

    public TreeNodeEx(LABEL label, boolean open, int flags, String... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TreeNodeEx(LABEL label, boolean open, int flags, UIComponents... components) {
        super(label, open, components);
        this.flags = flags;
    }

    public TreeNodeEx(LABEL label, boolean open, int flags, UIComponent... components) {
        super(label, open, components);
        this.flags = flags;
    }
}
