package com.virtual_hex.editor.data;

public class RadioButton<LABEL> extends AbstractUIComponent {

    public int value;
    public LABEL[] stringLabels;

    public RadioButton() {
    }

    public RadioButton(LABEL... stringLabels) {
        this.value = 0;
        this.stringLabels = stringLabels;
    }

    public RadioButton(int value, LABEL[] stringLabels) {
        this.value = value;
        this.stringLabels = stringLabels;
    }
}
