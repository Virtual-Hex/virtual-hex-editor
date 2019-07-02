package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L563
 * Columns
 * - You can also use SameLine(pos_x) to mimic simplified rows.
 * - The rows API is work-in-progress and rather lacking (rows are arguably the worst part of dear imgui at the moment!)
 */
@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName = "of")
@RequiredArgsConstructor(staticName="of")
public final class Columns extends AbstractUIComponent {

    @NonNull
    public String stringId;
    public int count;
    public boolean border;
}
