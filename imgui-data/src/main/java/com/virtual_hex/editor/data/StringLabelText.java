package com.virtual_hex.editor.data;

/**
 * simple formatted text
 */
public class StringLabelText extends StringLabel {

    public String text;

    public StringLabelText() {
    }

    public StringLabelText(String label, String text) {
        super(label);
        this.text = text;
    }
}
