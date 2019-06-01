package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.components.JaweOrderedDrawables;

/**
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
public class JaweTreeNodeEx extends JaweOrderedDrawables {

    public String label;
    public int flags;

    public JaweTreeNodeEx(String label) {
        this.label = label;
    }

    public JaweTreeNodeEx(String label, JImGuiDrawable... drawableElements) {
        super(drawableElements);
        this.label = label;
    }

    public JaweTreeNodeEx(String label, int flags) {
        this.label = label;
        this.flags = flags;
    }

    public JaweTreeNodeEx(String label, int flags, JImGuiDrawable... drawableElements) {
        super(drawableElements);
        this.label = label;
        this.flags = flags;
    }

    public JaweTreeNodeEx() {
    }

    public JaweTreeNodeEx(JImGuiDrawable[] drawableElements) {
        super(drawableElements);
    }
}
