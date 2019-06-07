package com.virtual_hex.data;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class Button extends Label {

    public int width;
    public int height;

    public Button() {
    }

    public Button(String label) {
        this.label = label;
    }

    public Button(String label, int width, int height) {
        this.label = label;
        this.width = width;
        this.height = height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Button button = (Button) o;

        if (width != button.width) return false;
        if (height != button.height) return false;
        return label != null ? label.equals(button.label) : button.label == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
