package com.virtual_hex.editor.data;

import lombok.*;

/**
 * button with FramePadding=(0,0) to easily embed within text
 * <p>
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L395
 * <p>
 * returning true when pressed and triggers activation handler
 */
@ToString

@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class SmallButton<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
}
