package com.virtual_hex.editor.data;

/**
 * Widgets: Combo BoxInt
 * - The new BeginCombo()/EndCombo() api allows you to manage your contents and buffer state however you want it, by creating e.g. Selectable() items.
 */
public class Combo extends LabeledComponents {

    public int flags = 0;
    public Selectable currentSelectable;

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
    public Combo(String label, int currentSelectedIndex, int flags, String... components) {
        super(label, components);
        this.flags = flags;
        this.currentSelectable = (Selectable) uiComponents.get(currentSelectedIndex);
    }

    /**
     * Current selected will be automatically added to the list, do not include it int the selectables
     *
     * @param label
     * @param currentSelectedIndex
     * @param flags
     * @param components
     */
    public Combo(String label, int currentSelectedIndex, int flags, UIComponents... components) {
        super(label, components);
        this.flags = flags;
        this.currentSelectable = (Selectable) uiComponents.get(currentSelectedIndex);
    }

    /**
     * Current selected will be automatically added to the list, do not include it int the selectables
     *
     * @param label
     * @param currentSelectedIndex
     * @param flags
     * @param components
     */
    public Combo(String label, int currentSelectedIndex, int flags, UIComponent... components) {
        super(label, components);
        this.flags = flags;
        this.currentSelectable = (Selectable) uiComponents.get(currentSelectedIndex);
    }
}
