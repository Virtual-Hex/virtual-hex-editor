package com.mr00anderson.editor.types;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;

public class JImGuiMenuItemDrawable implements JImGuiDrawable {

    public String label;
    public String shortcut;
    public boolean selected;
    public boolean enabled;

    public JImGuiMenuItemDrawable(String label) {
        this(label, false, true);
    }

    public JImGuiMenuItemDrawable(String label, boolean selected, boolean enabled){
        this(label, null, selected, enabled);
    }

    public JImGuiMenuItemDrawable(String label, String shortcut, boolean selected, boolean enabled) {
        this.label = label;
        this.shortcut = shortcut;
        this.selected = selected;
        this.enabled = enabled;
    }

    @Override
    public void draw(JImGui imGui, World world) {
        imGui.menuItem(label, shortcut, selected, enabled);
    }

    @Override
    public void dispose() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JImGuiMenuItemDrawable JImGuiMenuItemDrawable = (JImGuiMenuItemDrawable) o;

        if (selected != JImGuiMenuItemDrawable.selected) return false;
        if (enabled != JImGuiMenuItemDrawable.enabled) return false;
        if (label != null ? !label.equals(JImGuiMenuItemDrawable.label) : JImGuiMenuItemDrawable.label != null) return false;
        return shortcut != null ? shortcut.equals(JImGuiMenuItemDrawable.shortcut) : JImGuiMenuItemDrawable.shortcut == null;

    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (shortcut != null ? shortcut.hashCode() : 0);
        result = 31 * result + (selected ? 1 : 0);
        result = 31 * result + (enabled ? 1 : 0);
        return result;
    }
}
