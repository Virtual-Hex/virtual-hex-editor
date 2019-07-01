package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ColorPicker3<LABEL, COLOR> extends AbstractUIComponent {
    @NonNull
    public LABEL label;
    @NonNull
    public int flags = 0;
    @NonNull
    public COLOR color;
}
