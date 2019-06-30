package com.virtual_hex.editor;

import com.virtual_hex.editor.data.MenuBar;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import org.ice1000.jimgui.JImStr;

public class EditorMenuBar extends MenuBar<JImStr> {

    public EditorMenuBar() {
    }

    public EditorMenuBar(String string, boolean enabled) {
        super(new JImStr(string), enabled);
    }

    public EditorMenuBar(String string, boolean enabled, UIComponents... components) {
        super(new JImStr(string), enabled, components);
    }

    public EditorMenuBar(String string, boolean enabled, UIComponent... components) {
        super(new JImStr(string), enabled, components);
    }
}
