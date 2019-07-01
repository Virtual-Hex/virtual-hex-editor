package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class DragFloatRange<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public float value = 0;
    @NonNull
    public float value2 = 0;
    @NonNull
    public float valueSpeed = 1;
    @NonNull
    public float valueMin = Float.MIN_VALUE;
    @NonNull
    public float valueMax = Float.MAX_VALUE;
    @NonNull
    public LABEL format;
    @NonNull
    public LABEL format2;
    @NonNull
    public float power = 1.0f;
}
