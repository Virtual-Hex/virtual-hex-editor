package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class DragFloat<LABEL> extends InputFloat<LABEL> {

    @NonNull
    public LABEL label;
    @NonNull
    public float value;
    @NonNull
    public float valueSpeed = 1;
    @NonNull
    public float valueMin = Float.MIN_VALUE;
    @NonNull
    public float valueMax = Float.MAX_VALUE;
    @NonNull
    public LABEL format;
    @NonNull
    public float power = 1.0f;
}
