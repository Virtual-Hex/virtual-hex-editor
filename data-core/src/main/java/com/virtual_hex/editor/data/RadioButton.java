package com.virtual_hex.editor.data;

public class RadioButton extends UIComponent {

    public int value;
    public Label[] labels;

    public RadioButton() {
    }

    public RadioButton(String... labels) {
        this(0, getAsLabels(labels));
    }

    public RadioButton(int value, String... labels) {
        this(value, getAsLabels(labels));
    }

    public static Label[] getAsLabels(String... stringLabels) {
        int arraySize = stringLabels.length;
        Label[] labels = new Label[arraySize];
        for (int i = 0; i < arraySize; i++) {
            labels[i] = new Label(stringLabels[i]);
        }
        return labels;
    }

    public RadioButton(Label... labels) {
        this.value = 0;
        this.labels = labels;
    }

    public RadioButton(int value, Label[] labels) {
        this.value = value;
        this.labels = labels;
    }
}
