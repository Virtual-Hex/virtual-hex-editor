package com.virtual_hex.editor.data;

public class TreeNode extends Openable {

    public TreeNode() {
    }

    public TreeNode(String label, boolean open) {
        super(label, open);
    }

    public TreeNode(String label, boolean open, String... components) {
        super(label, open, components);
    }

    public TreeNode(String label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public TreeNode(String label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
