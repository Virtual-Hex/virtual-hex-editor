package com.virtual_hex.editor;

import com.virtual_hex.editor.data.MenuItemSelectable;
import org.ice1000.jimgui.JImStr;

public class EditorMenuItemSelectable extends MenuItemSelectable<JImStr> {

    public static final JImStr EMPTY = new JImStr("");

    public EditorMenuItemSelectable() {
    }

    public EditorMenuItemSelectable(String string) {
        super(new JImStr(string), EMPTY);
    }

    public EditorMenuItemSelectable(String string, boolean selected) {
        super(new JImStr(string), selected, EMPTY);
    }

    public EditorMenuItemSelectable(String string, boolean selected, boolean enabled) {
        super(new JImStr(string), selected, EMPTY, enabled);
    }

    public EditorMenuItemSelectable(String string, JImStr shortcut) {
        super(new JImStr(string), shortcut);
    }

    public EditorMenuItemSelectable(String string, boolean selected, JImStr shortcut) {
        super(new JImStr(string), selected, shortcut);
    }

    public EditorMenuItemSelectable(String string, boolean selected, JImStr shortcut, boolean enabled) {
        super(new JImStr(string), selected, shortcut, enabled);
    }
}
