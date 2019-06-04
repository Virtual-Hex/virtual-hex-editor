package com.mr00anderson.data;

/**
 *  shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class ColorText extends Text {

    public Vec4 color;

    public ColorText() {
    }

    public ColorText(String text) {
        super(text);
    }

    public ColorText(Vec4 color) {
        this.color = color;
    }

    public ColorText(String text, Vec4 color) {
        super(text);
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ColorText that = (ColorText) o;

        return color != null ? color.equals(that.color) : that.color == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        return result;
    }
}
