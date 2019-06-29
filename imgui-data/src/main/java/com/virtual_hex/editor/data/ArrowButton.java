package com.virtual_hex.editor.data;

public class ArrowButton<LABEL> extends Label<LABEL> {

    public int direction;

    public ArrowButton() {
    }

    public ArrowButton(LABEL label, int direction) {
        super(label);
        this.direction = direction;
    }
}
