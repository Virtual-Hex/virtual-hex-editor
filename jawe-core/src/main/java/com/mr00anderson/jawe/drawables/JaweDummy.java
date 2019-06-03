package com.mr00anderson.jawe.drawables;

/**
 * add a dummy item of given size. unlike InvisibleButton(), Dummy() won't take the mouse click or be navigable into.
 */
public class JaweDummy {

    public float width;
    public float height;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweDummy jaweDummy = (JaweDummy) o;

        if (Float.compare(jaweDummy.width, width) != 0) return false;
        return Float.compare(jaweDummy.height, height) == 0;
    }

    @Override
    public int hashCode() {
        int result = (width != +0.0f ? Float.floatToIntBits(width) : 0);
        result = 31 * result + (height != +0.0f ? Float.floatToIntBits(height) : 0);
        return result;
    }
}
