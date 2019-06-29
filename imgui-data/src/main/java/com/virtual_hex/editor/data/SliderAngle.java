package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L441
 */
public class SliderAngle<LABEL> extends InputFloat<LABEL> {

    public float valueDegreeMin;
    public float valueDegreeMax;

    public SliderAngle() {
    }

    public SliderAngle(LABEL label, float valueDegreeMin, float valueDegreeMax) {
        super(label);
        this.valueDegreeMin = valueDegreeMin;
        this.valueDegreeMax = valueDegreeMax;
    }

    public SliderAngle(LABEL label, float value, float valueDegreeMin, float valueDegreeMax) {
        super(label, value);
        this.valueDegreeMin = valueDegreeMin;
        this.valueDegreeMax = valueDegreeMax;
    }
}
