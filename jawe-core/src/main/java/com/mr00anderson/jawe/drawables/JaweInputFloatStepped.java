package com.mr00anderson.jawe.drawables;

public class JaweInputFloatStepped extends JaweInputFloat{

    public float step;
    public float stepFast;
    public int flags;

    public JaweInputFloatStepped() {
    }

    public JaweInputFloatStepped(String label, int value, int step, int stepFast, int flags) {
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

        JaweInputFloatStepped that = (JaweInputFloatStepped) o;

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
