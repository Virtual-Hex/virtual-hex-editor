package com.virtual_hex.data;

/**
 *
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L338
 *
 * call between widgets or groups to layout them horizontally. X position given in window coordinates.
 *
 */
public class SameLine implements UIData {

    public float positionX;
    public float spacingWidth = -1;

    public SameLine() {
    }

    public SameLine(float positionX) {
        this.positionX = positionX;
    }

    public SameLine(float positionX, float spacingWidth) {
        this.positionX = positionX;
        this.spacingWidth = spacingWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SameLine that = (SameLine) o;

        if (Float.compare(that.positionX, positionX) != 0) return false;
        return Float.compare(that.spacingWidth, spacingWidth) == 0;
    }

    @Override
    public int hashCode() {
        int result = (positionX != +0.0f ? Float.floatToIntBits(positionX) : 0);
        result = 31 * result + (spacingWidth != +0.0f ? Float.floatToIntBits(spacingWidth) : 0);
        return result;
    }
}
