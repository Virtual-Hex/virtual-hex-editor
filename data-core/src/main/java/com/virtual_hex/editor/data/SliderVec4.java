package com.virtual_hex.editor.data;

public class SliderVec4 extends Label {

    public Vec4 vec4;
    public float min;
    public float max;

    public SliderVec4() {
    }

    public SliderVec4(String label, Vec4 vec4, float min, float max) {
        super(label);
        this.vec4 = vec4;
        this.min = min;
        this.max = max;
    }
}
