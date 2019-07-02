package com.virtual_hex.editor.data;

// TODO New class, in extensions for window scaling

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class WindowDecorated<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public boolean open = false;
    @Builder.Default
    public int flags = 0;
    @NonNull
    public UIComponent[] uiComponents;

}
