package com.mr00anderson.data;

public class BeginTabItem {

   public String label;

   public Drawables drawables;

    public BeginTabItem() {
    }

    public BeginTabItem(String label, Drawables drawables) {
        this.label = label;
        this.drawables = drawables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeginTabItem that = (BeginTabItem) o;

        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        return result;
    }
}
