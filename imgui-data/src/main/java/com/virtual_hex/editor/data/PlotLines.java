package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class PlotLines extends AbstractUIComponent {

    @NonNull
    public String label;
    @NonNull
    public float[] values;
    @NonNull
    public int valueOffset;
    @NonNull
    public int valuesLength;
    public String overlayText;
    @NonNull
    public float scaleMin = Float.MIN_VALUE;
    @NonNull
    public float scaleMax = Float.MAX_VALUE;
    @NonNull
    public float graphWidth = 0;
    @NonNull
    public float graphHeight = 0;
}
