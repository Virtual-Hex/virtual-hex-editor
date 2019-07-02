package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L441
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class SliderAngle<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public float value = 0;
    @Builder.Default
    public float valueDegreeMin = Float.MIN_VALUE;
    @Builder.Default
    public float valueDegreeMax = Float.MAX_VALUE;
}
