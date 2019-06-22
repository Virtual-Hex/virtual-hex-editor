package com.virtual_hex.editor.data;


/**
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L535
 */
public class MenuItemSelectable extends MenuItem {

    /**
     * If the menu item is open or not
     */
    public boolean selected;

    public MenuItemSelectable() {
    }
    public MenuItemSelectable(String label) {
        super(label);
        this.selected = false;
        this.enabled = true;
    }


    public MenuItemSelectable(String label, boolean selected) {
        super(label);
        this.selected = selected;
        this.enabled = true;
    }

    public MenuItemSelectable(String label, boolean enabled, boolean selected) {
        super(label, enabled);
        this.selected = selected;
    }

    public MenuItemSelectable(String label, String shortcut, boolean enabled, boolean selected) {
        super(label, shortcut, enabled);
        this.selected = selected;
    }
}
