package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ListBox0<LABEL> extends AbstractUIComponent  {

    @NonNull
    public LABEL label;
    @NonNull
    public int width = 0;
    @NonNull
    public int height = 0;
    @NonNull
    public UIComponent[] uiComponents;
}
