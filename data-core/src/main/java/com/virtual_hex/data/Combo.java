package com.virtual_hex.data;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class Combo extends UIComponent {

    public String label;
    public int flags = 0;
    public UIComponentArray jaweSelectables;
    public Selectable currentSelectable;

    public Combo() {
    }

    public Combo(String label, UIComponentArray jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, int flags, UIComponentArray jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, UIComponentArray jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, int flags, UIComponentArray jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }
}
