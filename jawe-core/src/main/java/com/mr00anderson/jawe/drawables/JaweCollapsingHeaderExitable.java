package com.mr00anderson.jawe.drawables;

public class JaweCollapsingHeaderExitable extends JaweTreeNodeEx {

    /**
     * Can be null, if not null an X will appear
     */
    public boolean open;

    public JaweCollapsingHeaderExitable() {
    }

    public JaweCollapsingHeaderExitable(boolean open) {
        this.open = open;
    }

    public JaweCollapsingHeaderExitable(String label, int flags, JaweDrawables jaweDrawables, boolean open) {
        super(label, flags, jaweDrawables);
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        JaweCollapsingHeaderExitable that = (JaweCollapsingHeaderExitable) o;

        return open == that.open;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (open ? 1 : 0);
        return result;
    }
}
