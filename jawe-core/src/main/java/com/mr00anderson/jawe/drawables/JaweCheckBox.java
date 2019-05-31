package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

// Using native types in the editor means these types cannot be reused for anything else, maybe
// maybe create a parent JImGui
public class JaweCheckBox implements JaweDrawable {

    public String label;
    public NativeBool nativeBool = JaweStaticDeallocateManager.createBool(false);

    public JaweCheckBox() {
    }

    public JaweCheckBox(String label) {
        this.label = label;
    }

    public JaweCheckBox(String label, NativeBool nativeBool) {
        this.label = label;
        this.nativeBool = nativeBool;
    }

    @Override
    public void draw(JImGui imGui) {
        imGui.checkbox(label, nativeBool);
    }
}
