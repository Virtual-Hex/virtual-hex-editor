package com.virtual_hex.editor.data;

public class DragVec4<LABEL, VEC4> extends Label<LABEL> {

    public VEC4 bounds;
    public float speed = 1;
    public float min;
    public float max;

    public DragVec4() {
    }

    public DragVec4(LABEL label, VEC4 bounds) {
        super(label);
        this.bounds = bounds;
        this.speed = 1;
    }

    public DragVec4(LABEL label, VEC4 bounds, float speed, float min, float max) {
        super(label);
        this.bounds = bounds;
        this.speed = speed;
        this.min = min;
        this.max = max;
    }
}
