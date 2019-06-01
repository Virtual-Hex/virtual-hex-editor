package com.mr00anderson.jawe.drawables;

import org.ice1000.jimgui.JImGui;

public class JaweCollapsingHeader extends JaweTreeNodeEx {

    public JaweCollapsingHeader(String label) {
        super(label);
    }

    public JaweCollapsingHeader(String label, JImGuiDrawable... drawableElements) {
        super(label, drawableElements);
    }

    public JaweCollapsingHeader(String label, int flags) {
        super(label, flags);
    }

    public JaweCollapsingHeader(String label, int flags, JImGuiDrawable... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweCollapsingHeader() {
    }

    public JaweCollapsingHeader(JImGuiDrawable[] drawableElements) {
        super(drawableElements);
    }


    public void draw(JImGui imGui) {

    }
}
