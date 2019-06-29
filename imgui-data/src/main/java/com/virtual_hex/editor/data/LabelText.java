package com.virtual_hex.editor.data;

/**
 * simple formatted text
 */
public class LabelText<LABEL> extends Label<LABEL> {

    public LABEL text;

    public LabelText() {
    }

    public LabelText(LABEL label, LABEL text) {
        super(label);
        this.text = text;
    }
}
