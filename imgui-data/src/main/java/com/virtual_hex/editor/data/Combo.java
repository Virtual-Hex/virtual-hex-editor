package com.virtual_hex.editor.data;

import lombok.*;

/**
 * Widgets: Combo BoxInt
 * - The new BeginCombo()/EndCombo() api allows you to manage your contents and buffer state however you want it, by creating e.g. Selectable() items.
 */
@ToString
@Builder(toBuilder = true)
public final class Combo<LABEL> extends AbstractUIComponent{

    @NonNull
    public LABEL label;

    @NonNull
    public int flags = 0;

    @NonNull
    public Selectable<LABEL> currentSelectable;

    public Combo() {
    }

    /**
     * Current selected will be automatically added to the list, do not include it int the selectables
     *
     * @param label
     * @param currentSelectedIndex
     * @param flags
     * @param components
     */
    public Combo(LABEL label, int currentSelectedIndex, int flags, UIComponent... components) {
        this.flags = flags;
        this.currentSelectable = (Selectable) components[currentSelectedIndex];
    }


}
