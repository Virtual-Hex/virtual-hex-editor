package com.virtual_hex.data;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class Combo extends UIData {

    public String label;
    public int flags = 0;
    public UIDataArray jaweSelectables;
    public Selectable currentSelectable = new Selectable("");

    public Combo() {
    }

    public Combo(String label, UIDataArray jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, int flags, UIDataArray jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, UIDataArray jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, int flags, UIDataArray jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }
}
