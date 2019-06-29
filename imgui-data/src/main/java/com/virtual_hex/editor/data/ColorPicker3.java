package com.virtual_hex.editor.data;

public class ColorPicker3<LABEL, COLOR> extends ColorLabelFlags<LABEL, COLOR> {

    public ColorPicker3() {
    }

    public ColorPicker3(LABEL label, COLOR color) {
        super(label, color);
    }

    public ColorPicker3(LABEL label, COLOR color, int flags) {
        super(label, color, flags);
    }
}
