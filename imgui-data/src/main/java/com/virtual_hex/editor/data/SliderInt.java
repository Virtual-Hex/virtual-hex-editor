package com.virtual_hex.editor.data;


/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L442
 */
public class SliderInt<LABEL> extends InputInt<LABEL> {

    public int valueMin;
    public int valueMax;
    public LABEL format;

    public SliderInt() {
    }

    public SliderInt(LABEL label, int valueMin, int valueMax) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }

    public SliderInt(LABEL label, int value, int valueMin, int valueMax) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }

    public SliderInt(LABEL label, int valueMin, int valueMax, LABEL format) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
    }

    public SliderInt(LABEL label, int value, int valueMin, int valueMax, LABEL format) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
    }
}
