package com.virtual_hex.editor.data;

import lombok.*;

/**
 * simple formatted text
 */
@ToString
@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class Text<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
}
