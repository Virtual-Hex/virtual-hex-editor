package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Menu;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import org.ice1000.jimgui.JImStr;

public class EditorMenu extends Menu<JImStr> {

    public EditorMenu() {
    }

    public EditorMenu(String string) {
        super(new JImStr(string));
    }

    public EditorMenu(String string, UIComponents... components) {
        super(new JImStr(string), components);
    }

    public EditorMenu(String string, UIComponent... components) {
        super(new JImStr(string), components);
    }

    public EditorMenu(String string, boolean enabled) {
        super(new JImStr(string), enabled);
    }

    public EditorMenu(String string, boolean enabled, UIComponents... components) {
        super(new JImStr(string), enabled, components);
    }

    public EditorMenu(String string, boolean enabled, UIComponent... components) {
        super(new JImStr(string), enabled, components);
    }
}
