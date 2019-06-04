package com.virtual_hex.data;

/**
 *  Widgets: Combo Box
 *  - The new BeginCombo()/EndCombo() api allows you to manage your contents and selection state however you want it, by creating e.g. Selectable() items.
 */
public class Combo implements UIData {

    public String label;
    public int flags = 0;
    public UIDataList jaweSelectables;
    public Selectable currentSelectable = new Selectable("");

    public Combo() {
    }

    public Combo(String label, UIDataList jaweSelectables) {
        this.label = label;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, int flags, UIDataList jaweSelectables) {
        this.label = label;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, UIDataList jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.jaweSelectables = jaweSelectables;
    }

    public Combo(String label, Selectable currentSelectable, int flags, UIDataList jaweSelectables) {
        this.label = label;
        this.currentSelectable = currentSelectable;
        this.flags = flags;
        this.jaweSelectables = jaweSelectables;
    }
}
