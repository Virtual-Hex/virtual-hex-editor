package com.virtual_hex.editor.data;

public class ColorPicker4 extends ColorLabelFlags {

    public ColorPicker4() {
    }

    public ColorPicker4(String label) {
        super(label, new Vec4());
    }

    public ColorPicker4(String label, Vec4 color) {
        super(label, color);
    }

    public ColorPicker4(String label, Vec4 color, int flags) {
        super(label, color, flags);
    }
}
