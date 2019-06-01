package com.mr00anderson.jawe.drawables;

/**
 * You must put in a TREE_POP depending on how you want the tree to nest, if no tree pop it will infinitely nest.
 */
public class JaweTreeNodeExNoPop extends JaweTreeNodeEx {

    public JaweTreeNodeExNoPop(String label) {
        super(label);
    }

    public JaweTreeNodeExNoPop(String label, JImGuiDrawable... drawableElements) {
        super(label, drawableElements);
    }

    public JaweTreeNodeExNoPop(String label, int flags) {
        super(label, flags);
    }

    public JaweTreeNodeExNoPop(String label, int flags, JImGuiDrawable... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweTreeNodeExNoPop() {
    }

    public JaweTreeNodeExNoPop(JImGuiDrawable[] drawableElements) {
        super(drawableElements);
    }
}
