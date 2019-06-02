package com.mr00anderson.jawe.drawables;

public class JaweFlagedLabel extends JaweLabel {

    public int flags;

    public JaweFlagedLabel() {
    }

    public JaweFlagedLabel(String label) {
        super(label);
    }

    public JaweFlagedLabel(int flags) {
        this.flags = flags;
    }

    public JaweFlagedLabel(String label, int flags) {
        super(label);
        this.flags = flags;
    }

}
