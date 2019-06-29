package com.virtual_hex.editor.data;

public class DragFloatRange<LABEL> extends DragFloat<LABEL> {

    public float value2;
    public LABEL format2;

    public DragFloatRange(LABEL label, LABEL format) {
        super(label, format);
        this.format2 = format;
    }

    public DragFloatRange(LABEL label, LABEL format, int value2) {
        super(label, format);
        this.format2 = format;
        this.value2 = value2;
    }

    public DragFloatRange(LABEL label, int valueSpeed, int valueMax, int valueMin, LABEL format, int value2) {
        super(label, valueSpeed, valueMax, valueMin, format);
        this.value2 = value2;
        this.format2 = format;
    }

    public DragFloatRange(LABEL label, float value, float valueSpeed, float valueMax, float valueMin, LABEL format, float power, int value2) {
        super(label, value, valueSpeed, valueMax, valueMin, format, power);
        this.value2 = value2;
        this.label = label;
        this.format2 = format;
    }

    public DragFloatRange(LABEL label, float value, float valueSpeed, float valueMax, float valueMin, LABEL format, float power, int value2, LABEL format2) {
        super(label, value, valueSpeed, valueMax, valueMin, format, power);
        this.value2 = value2;
        this.format2 = format2;
    }
}
