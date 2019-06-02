package com.mr00anderson.jawe.drawables;

public class JaweTabBar {

    public String label;
    public int flags;
    public JaweDrawables drawables;

    public JaweTabBar() {
    }

    public JaweTabBar(String label, JaweDrawables drawables) {
        this.label = label;
        this.drawables = drawables;
    }

    public JaweTabBar(String label, int flags, JaweDrawables drawables) {
        this.label = label;
        this.flags = flags;
        this.drawables = drawables;
    }
}
