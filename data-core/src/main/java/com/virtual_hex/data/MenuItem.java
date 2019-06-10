package com.virtual_hex.data;

public class MenuItem extends Label {


    /**
     * These are not currently processed by ImGui, JImGui or Jawe
     */
    public String shortcut;

    /**
     * If the menu item is open or not
     */
    public boolean selected;

    /**
     * If the menu item is enabled
     */
    public boolean enabled;

    public MenuItem() {
    }

    public MenuItem(String label) {
        super(label);
    }

    public MenuItem(String shortcut, boolean selected, boolean enabled) {
        this.shortcut = shortcut;
        this.selected = selected;
        this.enabled = enabled;
    }

    public MenuItem(String label, String shortcut, boolean selected, boolean enabled) {
        super(label);
        this.shortcut = shortcut;
        this.selected = selected;
        this.enabled = enabled;
    }

    //    public void draw(JImGui imGui) {
//        // Returns true on activation + toggle
//        // @see https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L535
//        if(imGui.menuItem(label, shortcut, selected, enabled) && onActivation != null){
//            onActivation.handle(this);
//        }
//    }


}
