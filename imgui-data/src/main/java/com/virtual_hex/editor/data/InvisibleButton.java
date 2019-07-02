package com.virtual_hex.editor.data;

import lombok.*;

/**
 * Button
 * <p>
 * returning true when pressed
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 * <p>
 * button behavior without the visuals, frequently useful to build custom behaviors using the public api (along with IsItemActive, IsItemHovered, etc.)
 */
@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class InvisibleButton<LABEL> extends AbstractUIComponent {
    @NonNull
    public LABEL label;
    public int width = 0;
    public int height = 0;
}
