package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ListBox<LABEL> extends AbstractUIComponent  {

    @NonNull
    public LABEL label;
    @NonNull
    public int itemsCount;
    @NonNull
    public int heightInItems = 0;
    @NonNull
    public UIComponents uiComponents;

}
