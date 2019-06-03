package com.mr00anderson.jawe.drawables;

public class JaweInputIntStepped extends JaweInputInt{

    public int step;
    public int stepFast;
    public int flags;

    public JaweInputIntStepped() {
    }

    public JaweInputIntStepped(String label, int value, int step, int stepFast, int flags) {
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

        JaweInputIntStepped that = (JaweInputIntStepped) o;

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
