package com.virtual_hex.editor.data;

public class DragFloat<LABEL> extends InputFloat<LABEL> {

    public float valueSpeed = 1;
    public float valueMax;
    public float valueMin;
    public LABEL format;
    public float power;

    public DragFloat() {
    }

    public DragFloat(LABEL label, LABEL format) {
        super(label);
        this.format = format;
    }


    public DragFloat(LABEL label, float valueSpeed, float valueMax, float valueMin, LABEL format) {
        super(label);
        this.valueSpeed = valueSpeed;
        this.valueMax = valueMax;
        this.valueMin = valueMin;
        this.format = format;
    }

    public DragFloat(LABEL label, float value, float valueSpeed, float valueMax, float valueMin, LABEL format, float power) {
        super(label, value);
        this.valueSpeed = valueSpeed;
        this.valueMax = valueMax;
        this.valueMin = valueMin;
        this.format = format;
        this.power = power;
    }
}
