package com.mr00anderson.jawe.drawables;

// Using native types in the editor means these types cannot be reused for anything else, maybe
// maybe create a parent JImGui
public class JaweCheckBox  {

    public String label;
    public boolean checked;

    public JaweCheckBox() {
    }

    public JaweCheckBox(String label) {
        this.label = label;
    }

    public JaweCheckBox(String label, boolean checked) {
        this.label = label;
        this.checked = checked;
    }
}
