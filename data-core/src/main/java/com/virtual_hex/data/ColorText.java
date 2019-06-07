package com.virtual_hex.data;

/**
 *  shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class ColorText extends Text {

    public Vec4 color;

    public ColorText(Vec4 color) {
        this.color = color;
    }

    public ColorText(String label, Vec4 color) {
        super(label);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColorText colorText = (ColorText) o;

        return color.equals(colorText.color);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + color.hashCode();
        return result;
    }
}
