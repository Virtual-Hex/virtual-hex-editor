package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

public class JaweCollapsingHeader extends JaweTreeNodeEx {

    public JaweCollapsingHeader(String label) {
        super(label);
    }

    public JaweCollapsingHeader(String label, JaweDrawable... drawableElements) {
        super(label, drawableElements);
    }

    public JaweCollapsingHeader(String label, int flags) {
        super(label, flags);
    }

    public JaweCollapsingHeader(String label, int flags, JaweDrawable... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweCollapsingHeader() {
    }

    public JaweCollapsingHeader(JaweDrawable[] drawableElements) {
        super(drawableElements);
    }

    @Override
    public void draw(JImGui imGui) {
        boolean isOpen = imGui.collapsingHeader(label);
        if(isOpen){
            drawables.forEach(d -> d.draw(imGui));
        }
    }
}
