package com.virtual_hex.data;

// Using native types in the editor means these types cannot be reused for anything else, maybe
// maybe create a parent JImGui
public class CheckBox extends UIComponent {

    public String label;
    public boolean value;

    public CheckBox() {
    }

    public CheckBox(String label) {
        this.label = label;
    }

    public CheckBox(String label, boolean value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CheckBox that = (CheckBox) o;

        if (value != that.value) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (value ? 1 : 0);
        return result;
    }
}
