package com.virtual_hex.editor.data;

public class ColorEdit3<LABEL, COLOR> extends ColorLabelFlags<LABEL, COLOR> {

    public ColorEdit3() {
    }

    public ColorEdit3(LABEL label, COLOR color) {
        super(label, color);
    }

    public ColorEdit3(LABEL label, COLOR color, int flags) {
        super(label, color, flags);
    }
}
