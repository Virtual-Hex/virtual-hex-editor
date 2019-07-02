package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Child<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean border;
    @NonNull
    public int flags = 0;
    @NonNull
    public int width = 0;
    @NonNull
    public int height = 0;
    @NonNull
    public UIComponent[] uiComponents;
}
