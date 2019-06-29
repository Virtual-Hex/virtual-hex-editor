package com.virtual_hex.editor.data;

public class PlotHistogram extends PlotLines {

    public PlotHistogram() {
    }

    public PlotHistogram(String label, float... values) {
        super(label, values);
    }

    public PlotHistogram(String label, String overlayText, float... values) {
        super(label, overlayText, values);
    }

    public PlotHistogram(String label, int valueOffset, int valuesLength, String overlayText, float scaleMin, float scaleMax, float graphWidth, float graphHeight, float... values) {
        super(label, valueOffset, valuesLength, overlayText, scaleMin, scaleMax, graphWidth, graphHeight, values);
    }

    public PlotHistogram(String label, float[] values, int valueOffset, int valuesLength, String overlayText, float scaleMin, float scaleMax, float graphWidth, float graphHeight) {
        super(label, values, valueOffset, valuesLength, overlayText, scaleMin, scaleMax, graphWidth, graphHeight);
    }

}
