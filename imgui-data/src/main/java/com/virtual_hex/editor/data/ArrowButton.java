package com.virtual_hex.editor.data;

public class ArrowButton extends Label {

    public int direction;

    public ArrowButton() {
    }

    public ArrowButton(String label, int direction) {
        super(label);
        this.direction = direction;
    }
}
