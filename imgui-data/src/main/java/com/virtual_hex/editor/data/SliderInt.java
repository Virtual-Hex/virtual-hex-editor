package com.virtual_hex.editor.data;


import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L442
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class SliderInt<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public int value = 0;
    @Builder.Default
    public int valueMin = Integer.MIN_VALUE;
    @Builder.Default
    public int valueMax = Integer.MAX_VALUE;
    @NonNull
    public LABEL format;
}
