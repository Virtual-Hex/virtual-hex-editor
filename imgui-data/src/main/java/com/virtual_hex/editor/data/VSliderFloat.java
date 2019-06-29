package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L437
 * <p>
 * djust format string to decorate the value with a prefix, a suffix, or adapt the editing and display precision e.g.
 * "%.3f" -> 1.234; "%5.2f secs" -> 01.23 secs; "Biscuit: %.0f" -> Biscuit: 1; etc.
 */
public class VSliderFloat<LABEL> extends InputFloat<LABEL> {

    public float width;
    public float height;
    public float valueMin;
    public float valueMax;
    public LABEL format;
    public float power;

    public VSliderFloat() {
        this.power = 1.0f;
    }

    public VSliderFloat(LABEL label, float width, float height, float valueMin, float valueMax) {
        super(label);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.power = 1.0f;
    }

    public VSliderFloat(LABEL label, float width, float height, float value, float valueMin, float valueMax) {
        super(label, value);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.power = 1.0f;
    }

    public VSliderFloat(LABEL label, float width, float height, float valueMin, float valueMax, LABEL format) {
        super(label);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = 1.0f;
    }

    public VSliderFloat(LABEL label, float width, float height, float value, float valueMin, float valueMax, LABEL format) {
        super(label, value);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = 1.0f;
    }

    public VSliderFloat(LABEL label, float width, float height, float valueMin, float valueMax, LABEL format, float power) {
        super(label);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = power;
    }

    public VSliderFloat(LABEL label, float width, float height, float value, float valueMin, float valueMax, LABEL format, float power) {
        super(label, value);
        this.height = height;
        this.width = width;
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = power;
    }
}
