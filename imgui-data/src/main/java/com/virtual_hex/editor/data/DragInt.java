package com.virtual_hex.editor.data;

public class DragInt<LABEL> extends InputInt<LABEL> {

    public int valueSpeed = 1;
    public int valueMax;
    public int valueMin;
    public LABEL format;

    public DragInt() {
    }

    public DragInt(LABEL label, LABEL format) {
        super(label);
        this.format = format;
    }


    public DragInt(LABEL label, int valueSpeed, int valueMax, int valueMin, LABEL format) {
        super(label);
        this.valueSpeed = valueSpeed;
        this.valueMax = valueMax;
        this.valueMin = valueMin;
        this.format = format;
    }

    public DragInt(LABEL label, int value, int valueSpeed, int valueMax, int valueMin, LABEL format) {
        super(label, value);
        this.valueSpeed = valueSpeed;
        this.valueMax = valueMax;
        this.valueMin = valueMin;
        this.format = format;
    }
}
