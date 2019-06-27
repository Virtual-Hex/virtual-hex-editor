package com.virtual_hex.editor.data;

/**
 * This class overrides hashcode and equals from its parent UIComponent
 */
public class Vec4 extends AbstractUIComponent {

    public float x, y, z, w;

    public Vec4() {
    }

    public Vec4(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vec4 vec4 = (Vec4) o;

        if (Float.compare(vec4.x, x) != 0) return false;
        if (Float.compare(vec4.y, y) != 0) return false;
        if (Float.compare(vec4.z, z) != 0) return false;
        return Float.compare(vec4.w, w) == 0;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        result = 31 * result + (z != +0.0f ? Float.floatToIntBits(z) : 0);
        result = 31 * result + (w != +0.0f ? Float.floatToIntBits(w) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Vec4{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                "} " + super.toString();
    }
}
