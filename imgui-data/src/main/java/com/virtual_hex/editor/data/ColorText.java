package com.virtual_hex.editor.data;

/**
 * shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
public class ColorText<COLOR> extends StringLabel {

    public COLOR color;

    public ColorText() {
    }

    public ColorText(String label) {
        super(label);
    }

    public ColorText(String label, COLOR color) {
        super(label);
        this.color = color;
    }
}
