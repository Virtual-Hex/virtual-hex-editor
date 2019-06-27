package com.virtual_hex.editor.data;

public class ColorPicker3 extends ColorLabelFlags {

    public ColorPicker3() {
    }

    public ColorPicker3(String label) {
        super(label, new Vec4());
    }

    public ColorPicker3(String label, Vec4 color) {
        super(label, color);
    }

    public ColorPicker3(String label, Vec4 color, int flags) {
        super(label, color, flags);
    }
}
