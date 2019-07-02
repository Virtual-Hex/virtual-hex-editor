package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName="of")
public final class ProgressBar extends AbstractUIComponent {

    @Builder.Default
    public float fraction = 0f;
    @Builder.Default
    public float width = -1;
    @Builder.Default
    public float height = 0;

    public String overlay;

}
