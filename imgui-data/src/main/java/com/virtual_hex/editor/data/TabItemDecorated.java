package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class TabItemDecorated<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean open = false;
    @NonNull
    public int flags = 0;
    @NonNull
    public UIComponent[] uiComponents;
}
