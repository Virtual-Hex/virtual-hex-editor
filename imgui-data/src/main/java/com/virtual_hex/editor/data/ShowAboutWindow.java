package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor(staticName="of")
@NoArgsConstructor(staticName = "of")
@Toggle(fieldName = "open")
public final class ShowAboutWindow extends AbstractUIComponent {

    public boolean open = false;
}
