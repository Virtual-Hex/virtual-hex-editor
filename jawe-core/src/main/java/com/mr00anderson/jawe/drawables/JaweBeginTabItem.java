package com.mr00anderson.jawe.drawables;

public class JaweBeginTabItem {

   public String label;

   public JaweDrawables drawables;

    public JaweBeginTabItem() {
    }

    public JaweBeginTabItem(String label, JaweDrawables drawables) {
        this.label = label;
        this.drawables = drawables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweBeginTabItem that = (JaweBeginTabItem) o;

        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        return result;
    }
}
