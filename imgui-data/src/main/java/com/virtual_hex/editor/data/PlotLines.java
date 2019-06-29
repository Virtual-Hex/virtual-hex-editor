package com.virtual_hex.editor.data;

public class PlotLines extends StringLabel {

    public float[] values;
    public int valueOffset;
    public int valuesLength;
    public String overlayText;
    public float scaleMin;
    public float scaleMax;
    public float graphWidth;
    public float graphHeight;

    public PlotLines() {
    }

    public PlotLines(String label, float... values) {
        super(label);
        this.values = values;
        this.valueOffset = 0;
        this.valuesLength = values.length;
        this.overlayText = null;
        this.scaleMin = Float.MAX_VALUE;
        this.scaleMax = Float.MAX_VALUE;
    }

    public PlotLines(String label, String overlayText, float... values) {
        super(label);
        this.values = values;
        this.valueOffset = 0;
        this.valuesLength = values.length;
        this.overlayText = overlayText;
        this.scaleMin = Float.MAX_VALUE;
        this.scaleMax = Float.MAX_VALUE;
    }


    public PlotLines(String label, int valueOffset, int valuesLength, String overlayText, float scaleMin, float scaleMax, float graphWidth, float graphHeight, float... values) {
        super(label);
        this.values = values;
        this.valueOffset = valueOffset;
        this.valuesLength = valuesLength;
        this.overlayText = overlayText;
        this.scaleMin = scaleMin;
        this.scaleMax = scaleMax;
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
    }

    public PlotLines(String label, float[] values, int valueOffset, int valuesLength, String overlayText, float scaleMin, float scaleMax, float graphWidth, float graphHeight) {
        super(label);
        this.values = values;
        this.valueOffset = valueOffset;
        this.valuesLength = valuesLength;
        this.overlayText = overlayText;
        this.scaleMin = scaleMin;
        this.scaleMax = scaleMax;
        this.graphWidth = graphWidth;
        this.graphHeight = graphHeight;
    }
}
