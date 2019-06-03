package com.mr00anderson.jawe.drawables;

public class JaweInputInt {

    public String label = "";
    public int value;

    // TODO INSERT HANDLER FOR VERIFICATION AND FEEDBACK

    public JaweInputInt() {
    }

    public JaweInputInt(String label, int value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweInputInt that = (JaweInputInt) o;

        if (value != that.value) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + value;
        return result;
    }
}
