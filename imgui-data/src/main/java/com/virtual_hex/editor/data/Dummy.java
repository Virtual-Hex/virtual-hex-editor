package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L341
 * <p>
 * add a dummy item of given size. unlike InvisibleButton(), Dummy() won't take the mouse click or be navigable into.
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Dummy extends AbstractUIComponent {

    @NonNull
    public float width;
    @NonNull
    public float height;
}
