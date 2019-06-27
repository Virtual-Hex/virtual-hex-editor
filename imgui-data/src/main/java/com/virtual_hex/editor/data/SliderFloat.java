package com.virtual_hex.editor.data;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L437
 * <p>
 * djust format string to decorate the value with a prefix, a suffix, or adapt the editing and display precision e.g.
 * "%.3f" -> 1.234; "%5.2f secs" -> 01.23 secs; "Biscuit: %.0f" -> Biscuit: 1; etc.
 */
public class SliderFloat extends InputFloat {

    public float valueMin;
    public float valueMax;
    public String format;
    public float power;

    public SliderFloat() {
        this.power = 1.0f;
    }

    public SliderFloat(String label, float valueMin, float valueMax) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.power = 1.0f;
    }

    public SliderFloat(String label, float value, float valueMin, float valueMax) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.power = 1.0f;
    }

    public SliderFloat(String label, float valueMin, float valueMax, String format) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = 1.0f;
    }

    public SliderFloat(String label, float value, float valueMin, float valueMax, String format) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = 1.0f;
    }

    public SliderFloat(String label, float valueMin, float valueMax, String format, float power) {
        super(label);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = power;
    }

    public SliderFloat(String label, float value, float valueMin, float valueMax, String format, float power) {
        super(label, value);
        this.valueMin = valueMin;
        this.valueMax = valueMax;
        this.format = format;
        this.power = power;
    }
}
