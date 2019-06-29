package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L400
 */
public class CheckBox<LABEL> extends Label<LABEL> {

    public boolean checked;

    public CheckBox() {
    }

    public CheckBox(LABEL label) {
        super(label);
    }

    public CheckBox(LABEL label, boolean checked) {
        super(label);
        this.checked = checked;
    }
}
