package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ColorEdit4<LABEL, COLOR> extends AbstractUIComponent{

    @NonNull
    public LABEL label;
    @Builder.Default
    public int flags = 0;
    @NonNull
    public COLOR color;
}
