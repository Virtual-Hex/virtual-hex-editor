package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@AllArgsConstructor(staticName="of")
@NoArgsConstructor(staticName = "of")
public final class ShowAboutWindow extends AbstractUIComponent {

    public boolean open = false;
}
