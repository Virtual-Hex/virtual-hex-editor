package com.virtual_hex.editor.data;

/**
 * simple formatted text
 */
public class LabelText extends Label {

    public String text;

    public LabelText() {
    }

    public LabelText(String label, String text) {
        super(label);
        this.text = text;
    }
}
