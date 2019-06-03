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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweBeginTabItem that = (JaweBeginTabItem) o;

        if (flags != that.flags) return false;
        if (open != that.open) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (open ? 1 : 0);
        return result;
    }
}
