package com.virtual_hex.editor.data;

import lombok.*;

@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class RadioButton<LABEL> extends AbstractUIComponent {

    public int value = 0;
    @NonNull
    public LABEL[] stringLabels;
}
