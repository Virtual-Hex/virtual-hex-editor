package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class SliderVec4<LABEL, VEC4> extends AbstractUIComponent{

    @NonNull
    public LABEL label;
    @NonNull
    public VEC4 value;
    @Builder.Default
    public float valueMin = Float.MIN_VALUE;
    @Builder.Default
    public float valueMax = Float.MAX_VALUE;
}
