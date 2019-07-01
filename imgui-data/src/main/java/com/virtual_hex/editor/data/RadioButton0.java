package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class RadioButton0<LABEL> extends AbstractUIComponent {
    @NonNull
    public int value = 0;
    @NonNull
    public LABEL[] stringLabels;
}
