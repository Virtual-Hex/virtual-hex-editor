package com.mr00anderson.jawe.drawables;

public class JaweBeginTabItem {

   public String label;
   public int flags;
   public boolean open;
   public JaweDrawables drawables;

    public JaweBeginTabItem() {
    }

    public JaweBeginTabItem(String label, int flags, boolean open, JaweDrawables drawables) {
        this.label = label;
        this.flags = flags;
        this.open = open;
        this.drawables = drawables;
    }
}
