package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class DragIntRange<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public int value = 0;
    @NonNull
    public int value2 = 0;
    @NonNull
    public int valueSpeed = 1;
    @NonNull
    public int valueMin = Integer.MIN_VALUE;
    @NonNull
    public int valueMax = Integer.MAX_VALUE;
    @NonNull
    public LABEL format;
    @NonNull
    public LABEL format2;
}
