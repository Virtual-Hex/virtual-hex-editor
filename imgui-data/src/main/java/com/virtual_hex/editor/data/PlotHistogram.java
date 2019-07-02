package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class PlotHistogram extends AbstractUIComponent {

    @NonNull
    public String label;
    @NonNull
    public float[] values;
    public int valueOffset;
    public int valuesLength;
    public String overlayText;
    @Builder.Default
    public float scaleMin = Float.MIN_VALUE;
    @Builder.Default
    public float scaleMax = Float.MAX_VALUE;
    @Builder.Default
    public float graphWidth = 0;
    @Builder.Default
    public float graphHeight = 0;
}
