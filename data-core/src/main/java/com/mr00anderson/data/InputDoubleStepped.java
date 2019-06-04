package com.mr00anderson.data;

public class InputDoubleStepped extends InputDouble {

    public double step;
    public double stepFast;
    public int flags;

    public InputDoubleStepped() {
    }

    public InputDoubleStepped(String label, int value, int step, int stepFast, int flags) {
        super(label, value);
        this.step = step;
        this.stepFast = stepFast;
        this.flags = flags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        InputDoubleStepped that = (InputDoubleStepped) o;

        if (Double.compare(that.step, step) != 0) return false;
        if (Double.compare(that.stepFast, stepFast) != 0) return false;
        return flags == that.flags;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(step);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(stepFast);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + flags;
        return result;
    }
}
