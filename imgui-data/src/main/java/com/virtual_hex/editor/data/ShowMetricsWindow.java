package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor(staticName="of")
@Toggle(fieldName = "open")
public final class ShowMetricsWindow extends AbstractUIComponent {

    public boolean open = false;
}
