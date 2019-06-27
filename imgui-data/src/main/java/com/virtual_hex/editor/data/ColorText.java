package com.virtual_hex.editor.data;

/**
 * shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class ColorText extends Text {

    public Vec4 color;

    public ColorText() {
    }

    public ColorText(String label) {
        super(label);
    }

    public ColorText(String label, Vec4 color) {
        super(label);
        this.color = color;
    }
}
