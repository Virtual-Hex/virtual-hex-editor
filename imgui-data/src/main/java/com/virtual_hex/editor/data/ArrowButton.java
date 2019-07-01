package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ArrowButton<LABEL, DIRECTION> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public DIRECTION direction;

    // TODO This field may have to be a translated enum because of boxing/generics
}
