package com.virtual_hex.editor.data;


import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L535
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class MenuItemSelectable<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    /**
     * If the menu item is open or not
     */
    @NonNull
    public boolean selected = false;
    @NonNull
    public LABEL shortcut;
    @NonNull
    public boolean enabled = true;
}
