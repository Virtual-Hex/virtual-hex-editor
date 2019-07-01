package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class InputText extends AbstractUIComponent {

    @NonNull
    public int flags;
    @NonNull
    public byte[] buffer;
}
