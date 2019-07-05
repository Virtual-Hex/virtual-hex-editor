package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
@Toggle(fieldName = "open")
public final class Window extends AbstractUIComponent {

    @NonNull
    public String label;
    public boolean open = false;
    @NonNull
    public UIComponent[] uiComponents;

}
