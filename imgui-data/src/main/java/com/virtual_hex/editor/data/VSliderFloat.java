package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L437
 * <p>
 * djust format string to decorate the value with a prefix, a suffix, or adapt the editing and display precision e.g.
 * "%.3f" -> 1.234; "%5.2f secs" -> 01.23 secs; "Biscuit: %.0f" -> Biscuit: 1; etc.
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class VSliderFloat<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public float value = 0;
    @Builder.Default
    public float width = 0;
    @Builder.Default
    public float height = 0;
    @Builder.Default
    public float valueMin = Float.MIN_VALUE;
    @Builder.Default
    public float valueMax = Float.MAX_VALUE;
    @NonNull
    public LABEL format;
    @Builder.Default
    public float power = 1.0f;
}
