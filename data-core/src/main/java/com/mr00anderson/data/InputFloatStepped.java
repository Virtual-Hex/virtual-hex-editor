package com.mr00anderson.data;

public class InputFloatStepped extends InputFloat {

    public float step;
    public float stepFast;
    public int flags;

    public InputFloatStepped() {
    }

    public InputFloatStepped(String label, int value, int step, int stepFast, int flags) {
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

        InputFloatStepped that = (InputFloatStepped) o;

        if (Float.compare(that.step, step) != 0) return false;
        if (Float.compare(that.stepFast, stepFast) != 0) return false;
        return flags == that.flags;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (step != +0.0f ? Float.floatToIntBits(step) : 0);
        result = 31 * result + (stepFast != +0.0f ? Float.floatToIntBits(stepFast) : 0);
        result = 31 * result + flags;
        return result;
    }
}
