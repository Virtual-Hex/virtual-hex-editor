package com.virtual_hex.editor.data;

import lombok.*;

import java.util.List;

/**
 * Widgets: Combo BoxInt
 * - The new BeginCombo()/EndCombo() api allows you to manage your contents and buffer state however you want it, by creating e.g. Selectable() items.
 */
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor(staticName="of")
@AllArgsConstructor(staticName="of")
@RequiredArgsConstructor(staticName="of")
public final class Combo<LABEL> extends AbstractUIComponent{

    @NonNull
    public LABEL label;

    @Builder.Default
    public int flags = 0;

    @NonNull
    public Selectable<LABEL> currentSelectable;

    public List<UIComponent> uiComponents;
}
