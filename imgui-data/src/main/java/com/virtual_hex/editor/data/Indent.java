package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
public final class Indent extends AbstractUIComponent {

    public float value;
}
