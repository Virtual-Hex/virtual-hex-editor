package com.virtual_hex.editor.data;

import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class MenuItem extends AbstractUIComponent {

    /**
     * These are not currently processed by ImGui, JImGui or Jawe
     */
    @NonNull
    public String label;
    @NonNull
    public String shortcut = "";
    @NonNull
    public boolean enabled = true;
}
