package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L579
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
@Toggle(fieldName = "open")
public final class TabItem extends AbstractUIComponent {

    @NonNull
    public String label;
    @Builder.Default
    public boolean open = false;
    @NonNull
    public UIComponent[] uiComponents;

}
