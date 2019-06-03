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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweCheckBox that = (JaweCheckBox) o;

        if (checked != that.checked) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        return result;
    }
}
