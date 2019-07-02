package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ListBox0<LABEL> extends AbstractUIComponent  {

    @NonNull
    public LABEL label;
    @Builder.Default
    public int width = 0;
    @Builder.Default
    public int height = 0;
    @NonNull
    public UIComponent[] uiComponents;
}
