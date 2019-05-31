package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

/**
 * You must put in a TREE_POP depending on how you want the tree to nest, if no tree pop it will infinitely nest.
 */
public class JaweTreeNodeExNoPop extends JaweTreeNodeEx {

    public JaweTreeNodeExNoPop(String label) {
        super(label);
    }

    public JaweTreeNodeExNoPop(String label, JaweDrawable... drawableElements) {
        super(label, drawableElements);
    }

    public JaweTreeNodeExNoPop(String label, int flags) {
        super(label, flags);
    }

    public JaweTreeNodeExNoPop(String label, int flags, JaweDrawable... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweTreeNodeExNoPop() {
    }

    public JaweTreeNodeExNoPop(JaweDrawable[] drawableElements) {
        super(drawableElements);
    }

    @Override
    public void draw(JImGui imGui) {
        boolean open = imGui.treeNodeEx(label, flags);
        if(open){
            drawables.forEach(d -> d.draw(imGui));
        }
    }
}
