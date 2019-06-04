package com.mr00anderson.data;

public class CollapsingHeaderExitable extends TreeNodeEx {

    /**
     * Can be null, if not null an X will appear
     */
    public boolean open;

    public CollapsingHeaderExitable() {
    }

    public CollapsingHeaderExitable(boolean open) {
        this.open = open;
    }

    public CollapsingHeaderExitable(String label, int flags, Drawables drawables, boolean open) {
        super(label, flags, drawables);
        this.open = open;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CollapsingHeaderExitable that = (CollapsingHeaderExitable) o;

        return open == that.open;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (open ? 1 : 0);
        return result;
    }
}
