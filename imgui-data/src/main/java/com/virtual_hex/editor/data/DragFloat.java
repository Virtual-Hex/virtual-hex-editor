package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class DragFloat<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    public float value;
    @Builder.Default
    public float valueSpeed = 1;
    @Builder.Default
    public float valueMin = Float.MIN_VALUE;
    @Builder.Default
    public float valueMax = Float.MAX_VALUE;
    @NonNull
    public LABEL format;
    @Builder.Default
    public float power = 1.0f;
}
