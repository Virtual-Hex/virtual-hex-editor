package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@RequiredArgsConstructor(staticName="of")
public final class Selectable<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @NonNull
    public boolean selected = false;
    @NonNull
    public int flags = 0;

    public static Selectable[] fromStrings(String... strings){
        Selectable[] selectables = new Selectable[strings.length];
        for (int i = 0; i < strings.length; i++) {
            selectables[i] = new Selectable(strings[i]);
        }
        return selectables;
    }
}
