package com.virtual_hex.data;

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

    public CollapsingHeaderExitable(String label, int flags, UIDataList UIDataList, boolean open) {
        super(label, flags, UIDataList);
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
