package com.virtual_hex.editor.data;

public class Label<T> extends AbstractUIComponent {

    public T label;

    public Label() {
    }

    public Label(T label) {
        this.label = label;
    }

}
