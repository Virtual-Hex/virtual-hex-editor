package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class JaweSelectable implements JaweDrawable {

    public String label;
    public NativeBool selected = JaweStaticDeallocateManager.createBool(false);
    public int flags;
    public int width;
    public int height;

    /**
     * This will be triggered when selected or deselected
     */
    public ActivationHandler<JaweSelectable> onActivation = imGuiDrawable -> {};

    public JaweSelectable() {
    }

    public JaweSelectable(String label) {
        this.label = label;
    }

    public JaweSelectable(String label, int flags) {
        this.label = label;
        this.flags = flags;
    }

    public JaweSelectable(String label, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.onActivation = onActivation;
    }

    public JaweSelectable(String label, int flags, ActivationHandler<JaweSelectable> onActivation) {
        this.label = label;
        this.flags = flags;
        this.onActivation = onActivation;
    }

    @Override
    public void draw(JImGui imGui) {
        //  returning the state true when selected or false when unselected
        //  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
        if(imGui.selectable(label, selected, flags, width, height)){
            onActivation.handle(this);
        }
    }


}
