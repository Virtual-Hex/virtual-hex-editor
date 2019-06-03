package com.mr00anderson.jawe.drawables;

public class JaweInputFloat {

    public String label = "";
    public float value;

    // TODO INSERT HANDLER FOR VERIFICATION AND FEEDBACK

    public JaweInputFloat() {
    }

    public JaweInputFloat(String label, float value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweInputFloat that = (JaweInputFloat) o;

        if (Float.compare(that.value, value) != 0) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (value != +0.0f ? Float.floatToIntBits(value) : 0);
        return result;
    }
}
