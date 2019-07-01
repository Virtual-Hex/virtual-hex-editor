package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class DragVec4<LABEL, VEC4> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public VEC4 bounds;
    @NonNull
    public float speed = 1;
    @NonNull
    public float min = Float.MIN_VALUE;
    @NonNull
    public float max = Float.MAX_VALUE;
}
