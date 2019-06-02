package com.mr00anderson.jawe.drawables;

public class JaweCollapsingHeaderExitable extends JaweTreeNodeEx {

    /**
     * Can be null, if not null an X will appear
     */
    public boolean open;

    public JaweCollapsingHeaderExitable() {
    }

    public JaweCollapsingHeaderExitable(boolean open) {
        this.open = open;
    }

    public JaweCollapsingHeaderExitable(String label, int flags, JaweDrawables jaweDrawables, boolean open) {
        super(label, flags, jaweDrawables);
        this.open = open;
    }
}
