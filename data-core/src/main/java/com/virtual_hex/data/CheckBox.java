package com.virtual_hex.data;

// Using native types in the editor means these types cannot be reused for anything else, maybe
// maybe create a parent JImGui
public class CheckBox extends Label {

    public String label;

    public CheckBox() {
    }

    public CheckBox(String label) {
        this.label = label;
    }

    public CheckBox(String label, String label1) {
        super(label);
        this.label = label1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CheckBox checkBox = (CheckBox) o;

        return label.equals(checkBox.label);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }
}
