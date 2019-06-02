package com.mr00anderson.jawe.drawables;

/**
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
public class JaweTreeNodeEx {

    public String label;
    public int flags;
    public JaweDrawables drawables;

    public JaweTreeNodeEx() {
    }

    public JaweTreeNodeEx(String label, int flags, JaweDrawables drawables) {
        this.label = label;
        this.flags = flags;
        this.drawables = drawables;
    }
}
