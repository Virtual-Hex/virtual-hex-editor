package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L466
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class InputDoubleStepped<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public double value = 0d;
    @Builder.Default
    public double step = 1d;
    @Builder.Default
    public double stepFast = 1d;
    @Builder.Default
    public int flags = 0;
    @NonNull
    public LABEL format;
}
