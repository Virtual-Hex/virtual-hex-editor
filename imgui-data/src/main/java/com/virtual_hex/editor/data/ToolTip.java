package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ToolTip extends AbstractUIComponent {

    @NonNull
    public UIComponents uiComponents;
}
