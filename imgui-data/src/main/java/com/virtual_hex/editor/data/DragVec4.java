package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class DragVec4<LABEL, VEC4> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public VEC4 bounds;
    @Builder.Default
    public float speed = 1;
    @Builder.Default
    public float min = Float.MIN_VALUE;
    @Builder.Default
    public float max = Float.MAX_VALUE;
}
