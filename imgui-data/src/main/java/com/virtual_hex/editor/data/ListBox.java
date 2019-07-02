package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ListBox<LABEL> extends AbstractUIComponent  {

    @NonNull
    public LABEL label;
    public int itemsCount;
    @Builder.Default
    public int heightInItems = 0;
    @NonNull
    public UIComponent[] uiComponents;

}
