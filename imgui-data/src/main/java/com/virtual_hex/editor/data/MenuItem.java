package com.virtual_hex.editor.data;

public class MenuItem extends Label {

    /**
     * These are not currently processed by ImGui, JImGui or Jawe
     */
    public String shortcut;
    public boolean enabled;

    public MenuItem() {
    }

    public MenuItem(String label) {
        super(label);
        this.enabled = true;
    }

    public MenuItem(String label, boolean enabled) {
        super(label);
        this.enabled = enabled;
    }

    public MenuItem(String label, String shortcut, boolean enabled) {
        super(label);
        this.shortcut = shortcut;
        this.enabled = enabled;
    }
}
