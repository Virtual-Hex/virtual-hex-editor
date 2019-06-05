package com.virtual_hex.data;

/**
 * add a dummy item of given size. unlike InvisibleButton(), Dummy() won't take the mouse click or be navigable into.
 */
public class Dummy extends UIComponent {

    public float width;
    public float height;

    public Dummy() {
    }

    public Dummy(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dummy dummy = (Dummy) o;

        if (Float.compare(dummy.width, width) != 0) return false;
        return Float.compare(dummy.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result = (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        return result;
    }
}
