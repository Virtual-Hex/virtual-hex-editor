package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Window extends AbstractUIComponent {

    @NonNull
    public String label;
    @NonNull
    public boolean open = false;
    @NonNull
    public UIComponents uiComponents;

    Unindent u = new Unindent();

}
