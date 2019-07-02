package com.virtual_hex.editor.data;

import lombok.*;

/**
 * Button
 * <p>
 * returning true when pressed
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class ColorButton<LABEL, COLOR> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public int width = 0;
    @Builder.Default
    public int height = 0;
    @Builder.Default
    public int flags = 0;
    @NonNull
    public COLOR color;
}
