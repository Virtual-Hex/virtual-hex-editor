package com.virtual_hex.data;

public class InputIntStepped extends InputInt {

    public int step;
    public int stepFast;
    public int flags;

    public InputIntStepped() {
    }

    public InputIntStepped(String label, int value, int step, int stepFast, int flags) {
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

        InputIntStepped that = (InputIntStepped) o;

        if (step != that.step) return false;
        if (stepFast != that.stepFast) return false;
        return flags == that.flags;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + step;
        result = 31 * result + stepFast;
        result = 31 * result + flags;
        return result;
    }
}
