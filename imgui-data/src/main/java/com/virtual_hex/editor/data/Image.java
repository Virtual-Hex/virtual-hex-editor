package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Image<LABEL, IMAGE_PATH> extends AbstractUIComponent{

    @NonNull
    public LABEL label;
    @NonNull
    public IMAGE_PATH from;
    @NonNull
    public int width;
    @NonNull
    public int height;
    @NonNull
    public float uv0x = 0;
    @NonNull
    public float uv0y = 0;
    @NonNull
    public float uv1x = 1;
    @NonNull
    public float uv1y = 1;
}
