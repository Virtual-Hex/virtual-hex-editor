package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ProgressBar extends AbstractUIComponent {

    @NonNull
    public float fraction = 0f;
    @NonNull
    public float width = -1;
    @NonNull
    public float height = 0;
    public String overlay = null;

}
