package com.virtual_hex.editor.data;

import lombok.*;

@ToString

@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class MenuItem extends AbstractUIComponent {

    /**
     * These are not currently processed by ImGui, JImGui or Jawe
     */
    @NonNull
    public String label;
    public String shortcut;
    public boolean enabled = true;
}
