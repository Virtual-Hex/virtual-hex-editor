package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class JaweCollapsingHeaderExitable extends JaweTreeNodeEx {

    /**
     * Can be null, if not null an X will appear
     */
    public NativeBool nativeBool = JaweStaticDeallocateManager.createBool(true);

    public JaweCollapsingHeaderExitable(String label) {
        super(label);
    }

    public JaweCollapsingHeaderExitable(String label, JaweDrawable... drawableElements) {
        super(label, drawableElements);
    }

    public JaweCollapsingHeaderExitable(String label, int flags) {
        super(label, flags);
    }

    public JaweCollapsingHeaderExitable(String label, int flags, JaweDrawable... drawableElements) {
        super(label, flags, drawableElements);
    }

    public JaweCollapsingHeaderExitable() {
    }

    public JaweCollapsingHeaderExitable(JaweDrawable[] drawableElements) {
        super(drawableElements);
    }


    public void draw(JImGui imGui) {
        boolean isOpen = imGui.collapsingHeader(label, nativeBool, flags);
        if(isOpen){
            drawables.forEach(d -> d.draw(imGui, d));
        }
    }
}
