package com.virtual_hex.editor.data;


import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L533
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Menu<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean enabled = true;
    @NonNull
    public int flags = 0;
    @NonNull
    public UIComponents uiComponents;

}