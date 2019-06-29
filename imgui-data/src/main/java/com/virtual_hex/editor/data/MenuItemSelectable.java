package com.virtual_hex.editor.data;


/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L535
 */
public class MenuItemSelectable<LABEL> extends Label<LABEL> {

    /**
     * If the menu item is open or not
     */
    public boolean selected;
    public LABEL shortcut;
    public boolean enabled;

    public MenuItemSelectable() {
    }
    public MenuItemSelectable(LABEL label, LABEL shortcut) {
        super(label);
        this.selected = false;
        this.shortcut = shortcut;
        this.enabled = true;
    }

    public MenuItemSelectable(LABEL label, boolean selected, LABEL shortcut) {
        super(label);
        this.selected = selected;
        this.shortcut = shortcut;
        this.enabled = true;
    }

    public MenuItemSelectable(LABEL label, boolean selected, LABEL shortcut, boolean enabled) {
        super(label);
        this.selected = selected;
        this.shortcut = shortcut;
        this.enabled = enabled;
    }
}
