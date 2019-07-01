package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L487
 * <p>
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class TreeNodeEx<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean open = false;
    @NonNull
    public int flags = 0;
    @NonNull
    public UIComponents uiComponents = UIComponents.EMPTY;

}
