package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class InputText extends AbstractUIComponent {

    @NonNull
    public String label;
    public int flags;
    @NonNull
    public byte[] buffer;
}
