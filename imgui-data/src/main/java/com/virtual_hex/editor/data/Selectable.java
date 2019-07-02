package com.virtual_hex.editor.data;

import lombok.*;

/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L504
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class Selectable<LABEL> extends AbstractUIComponent {

    @NonNull
    public LABEL label;
    @Builder.Default
    public boolean selected = false;
    @Builder.Default
    public int width = 0;
    @Builder.Default
    public int height = 0;
    @Builder.Default
    public int flags = 0;

    public static Selectable[] fromStrings(String... strings){
        Selectable<String>[] selectables = new Selectable[strings.length];
        for (int i = 0; i < strings.length; i++) {
            selectables[i] = Selectable.of(strings[i]);
        }
        return selectables;
    }

    public enum SelectableFlags {
        Nothing(0),
        /** Clicking this don't close parent popup window */
        DontClosePopups(1),
        /** Selectable frame can span all columns (text will still fit in current column) */
        SpanAllColumns(1 << 1),
        /** Generate press events on double clicks too */
        AllowDoubleClick(1 << 2);

        public int value;

        SelectableFlags(int value) {
            this.value = value;
        }
    }
}
