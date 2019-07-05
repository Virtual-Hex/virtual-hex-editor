package com.virtual_hex.editor.data;

import com.virtual_hex.editor.Toggle;
import lombok.*;

@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
@Toggle(fieldName = "open")
public final class TreeNode<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    public boolean open = false;
    @NonNull
    public UIComponent[] uiComponents;

}
