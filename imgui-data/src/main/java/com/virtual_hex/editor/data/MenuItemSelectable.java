package com.virtual_hex.editor.data;


import com.virtual_hex.editor.Toggle;
import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L535
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
@Toggle(fieldName = "selected")
public final class MenuItemSelectable<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    /**
     * If the menu item is open or not
     */
    @Builder.Default
    public boolean selected = false;
    @NonNull
    public LABEL shortcut;
    @Builder.Default
    public boolean enabled = true;
}
