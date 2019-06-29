package com.virtual_hex.editor.data;

public class ColorEdit4<LABEL, COLOR> extends ColorLabelFlags<LABEL, COLOR> {

    public ColorEdit4() {
    }

    public ColorEdit4(LABEL label, COLOR color) {
        super(label, color);
    }

    public ColorEdit4(LABEL label, COLOR color, int flags) {
        super(label, color, flags);
    }
}
