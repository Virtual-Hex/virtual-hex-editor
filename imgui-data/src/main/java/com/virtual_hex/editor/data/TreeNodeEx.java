package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L487
 * <p>
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
@Toggle(fieldName = "open")
public final class TreeNodeEx<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public boolean open = false;
    @Builder.Default
    public int flags = 0;
    @NonNull
    public UIComponent[] uiComponents;

}
