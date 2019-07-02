package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L338
 * <p>
 * call between widgets or groups to layout them horizontally. X position given in window coordinates.
 */
@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
public final class SameLine extends AbstractUIComponent {

    public float positionX = 0;
    public float spacingWidth = -1;
}
