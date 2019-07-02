package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class DragFloatRange<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public float value = 0;
    @Builder.Default
    public float value2 = 0;
    @Builder.Default
    public float valueSpeed = 1;
    @Builder.Default
    public float valueMin = Float.MIN_VALUE;
    @Builder.Default
    public float valueMax = Float.MAX_VALUE;
    @NonNull
    public LABEL format;
    @NonNull
    public LABEL format2;
    @Builder.Default
    public float power = 1.0f;
}
