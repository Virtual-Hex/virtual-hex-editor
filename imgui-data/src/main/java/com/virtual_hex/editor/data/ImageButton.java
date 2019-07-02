package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ImageButton<LABEL, IMAGE_PATH> extends AbstractUIComponent {
    @NonNull
    public LABEL label;
    @NonNull
    public IMAGE_PATH from;
    public int width;
    public int height;
    @Builder.Default
    public float uv0x = 0;
    @Builder.Default
    public float uv0y = 0;
    @Builder.Default
    public float uv1x = 1;
    @Builder.Default
    public float uv1y = 1;
    @Builder.Default
    public int framePadding = 0;
}