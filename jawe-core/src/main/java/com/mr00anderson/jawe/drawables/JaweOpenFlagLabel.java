package com.mr00anderson.jawe.drawables;

public class JaweOpenFlagLabel extends JaweFlagedLabel {

    public boolean opened;

    public JaweOpenFlagLabel() {
    }

    public JaweOpenFlagLabel(String label) {
        super(label);
    }

    public JaweOpenFlagLabel(int flags) {
        super(flags);
    }

    public JaweOpenFlagLabel(String label, int flags) {
        super(label, flags);
    }

    public JaweOpenFlagLabel(boolean opened) {
        this.opened = opened;
    }

    public JaweOpenFlagLabel(String label, boolean opened) {
        super(label);
        this.opened = opened;
    }

    public JaweOpenFlagLabel(int flags, boolean opened) {
        super(flags);
        this.opened = opened;
    }

    public JaweOpenFlagLabel(String label, int flags, boolean opened) {
        super(label, flags);
        this.opened = opened;
    }
}
