package com.virtual_hex.editor.data;


/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L442
 */
public class SliderInt extends InputInt {

    public int valueMin;
    public int valueMax;
    public String format;

    public SliderInt() {
    }

    public SliderInt(String label, int valueMin, int valueMax) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }

    public SliderInt(String label, int value, int valueMin, int valueMax) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
    }

    public SliderInt(String label, int valueMin, int valueMax, String format) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
    }

    public SliderInt(String label, int value, int valueMin, int valueMax, String format) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
    }
}
