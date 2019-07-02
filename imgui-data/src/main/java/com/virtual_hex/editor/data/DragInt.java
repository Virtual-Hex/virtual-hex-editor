package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class DragInt<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public int value = 0;
    @Builder.Default
    public int valueSpeed = 1;
    @Builder.Default
    public int valueMin = Integer.MIN_VALUE;
    @Builder.Default
    public int valueMax = Integer.MAX_VALUE;
    @NonNull
    public LABEL format;
}
