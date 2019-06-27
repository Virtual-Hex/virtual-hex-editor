package com.virtual_hex.editor.data;

public class ColorEdit4 extends ColorLabelFlags {

    public ColorEdit4() {
    }

    public ColorEdit4(String label) {
        super(label, new Vec4());
    }

    public ColorEdit4(String label, Vec4 color) {
        super(label, color);
    }

    public ColorEdit4(String label, Vec4 color, int flags) {
        super(label, color, flags);
    }
}
