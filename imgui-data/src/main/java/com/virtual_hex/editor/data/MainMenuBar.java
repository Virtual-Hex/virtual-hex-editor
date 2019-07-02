package com.virtual_hex.editor.data;


import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L529
 */
@ToString

@NoArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class MainMenuBar extends AbstractUIComponent{

    @NonNull
    public UIComponent[] uiComponents;

}
