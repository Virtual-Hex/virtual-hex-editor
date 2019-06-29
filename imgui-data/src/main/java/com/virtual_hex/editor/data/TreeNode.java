package com.virtual_hex.editor.data;

public class TreeNode<LABEL> extends Openable<LABEL> {

    public TreeNode() {
    }

    public TreeNode(LABEL label, boolean open) {
        super(label, open);
    }

    public TreeNode(LABEL label, boolean open, String... components) {
        super(label, open, components);
    }

    public TreeNode(LABEL label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public TreeNode(LABEL label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
