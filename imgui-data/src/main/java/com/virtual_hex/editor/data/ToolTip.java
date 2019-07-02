package com.virtual_hex.editor.data;

import lombok.*;

@ToString

@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ToolTip extends AbstractUIComponent {

    @NonNull
    public UIComponent[] uiComponents;
}
