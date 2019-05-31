package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.components.JaweOrderedDrawables;
import org.ice1000.jimgui.JImGui;

public class JaweTreeNodeEx extends JaweOrderedDrawables<JaweDrawable> {

    public String label;
    public int flags;

    public JaweTreeNodeEx(String label) {
        this.label = label;
    }

    public JaweTreeNodeEx(String label, JaweDrawable... drawableElements) {
        super(drawableElements);
        this.label = label;
    }

    public JaweTreeNodeEx(String label, int flags) {
        this.label = label;
        this.flags = flags;
    }

    public JaweTreeNodeEx(String label, int flags, JaweDrawable... drawableElements) {
        super(drawableElements);
        this.label = label;
        this.flags = flags;
    }

    public JaweTreeNodeEx() {
    }

    public JaweTreeNodeEx(JaweDrawable[] drawableElements) {
        super(drawableElements);
    }

    @Override
    public void draw(JImGui imGui) {
        boolean open = imGui.treeNodeEx(label, flags);
        if(open){
            drawables.forEach(d -> d.draw(imGui));
            imGui.treePop();
        }
    }
}
