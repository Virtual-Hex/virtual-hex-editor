package com.virtual_hex.editor.data;

public class ColorEdit3 extends ColorLabelFlags {

    public ColorEdit3() {
    }

    public ColorEdit3(String label) {
        super(label, new Vec4());
    }

    public ColorEdit3(String label, Vec4 color) {
        super(label, color);
    }

    public ColorEdit3(String label, Vec4 color, int flags) {
        super(label, color, flags);
    }
}
