package com.virtual_hex.editor.data;

import lombok.*;

@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
public final class Unindent extends AbstractUIComponent {

    public float value;
}
