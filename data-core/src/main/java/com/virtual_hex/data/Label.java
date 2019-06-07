package com.virtual_hex.data;

public class Label extends UIComponent {

    public static final String EMPTY = "";

    public String label = EMPTY;

    public Label() {
    }

    public Label(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Label label1 = (Label) o;

        return label.equals(label1.label);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + label.hashCode();
        return result;
    }
}
