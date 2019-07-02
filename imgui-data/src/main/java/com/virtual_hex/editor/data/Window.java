package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class Window extends AbstractUIComponent {

    @NonNull
    public String label;
    @Builder.Default
    public boolean open = false;
    @NonNull
    public UIComponent[] uiComponents;

}
