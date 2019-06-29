package com.virtual_hex.editor.data;

public class DragIntRange<LABEL> extends DragInt<LABEL> {

    public int value2;

    public DragIntRange(LABEL label, LABEL format) {
        super(label, format);
    }

    public DragIntRange(LABEL label, LABEL format, int value2) {
        super(label, format);
        this.value2 = value2;
    }

    public DragIntRange(LABEL label, int valueSpeed, int valueMax, int valueMin, LABEL format, int value2) {
        super(label, valueSpeed, valueMax, valueMin, format);
        this.value2 = value2;
    }

    public DragIntRange(LABEL label, int value, int valueSpeed, int valueMax, int valueMin, LABEL format, int value2) {
        super(label, value, valueSpeed, valueMax, valueMin, format);
        this.value2 = value2;
    }
}
