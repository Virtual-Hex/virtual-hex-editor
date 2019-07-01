package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ShowFontSelector<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
}