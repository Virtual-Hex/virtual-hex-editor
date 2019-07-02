package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@AllArgsConstructor(staticName="of")
@NoArgsConstructor(staticName = "of")
public final class ShowStyleEditor<STYLE> extends AbstractUIComponent {

    public STYLE style;
}
