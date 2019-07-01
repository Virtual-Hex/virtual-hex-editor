package com.virtual_hex.editor.data;

import lombok.*;

/**
 * simple formatted text
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class LabelText<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public LABEL text;
}
