package com.mr00anderson.jawe.drawables;

public class JaweInputDouble {

    public String label = "";
    public double value;

    // TODO INSERT HANDLER FOR VERIFICATION AND FEEDBACK

    public JaweInputDouble() {
    }

    public JaweInputDouble(String label, double value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweInputDouble that = (JaweInputDouble) o;

        if (Double.compare(that.value, value) != 0) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = label != null ? label.hashCode() : 0;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
