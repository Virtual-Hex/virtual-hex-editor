package com.virtual_hex.editor.data;

import lombok.*;

/**
 * shortcut for PushStyleColor(ImGuiCol_Text, col); Text(fmt, ...); PopStyleColor();
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class ColorText<COLOR> extends AbstractUIComponent {

    @NonNull
    public String label;
    @NonNull
    public COLOR color;
}
