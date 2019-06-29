package com.virtual_hex.editor.data;

public class ColorPicker4<LABEL, COLOR> extends ColorLabelFlags<LABEL, COLOR> {

    public ColorPicker4() {
    }

    public ColorPicker4(LABEL label, COLOR color) {
        super(label, color);
    }

    public ColorPicker4(LABEL label, COLOR color, int flags) {
        super(label, color, flags);
    }
}
