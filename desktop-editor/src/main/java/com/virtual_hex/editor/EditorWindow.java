package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.WindowDecorated;
import org.ice1000.jimgui.JImStr;

public class EditorWindow extends WindowDecorated {

    public EditorWindow() {
    }

    public EditorWindow(String name, UIComponent... components) {
        super(new JImStr(name), false, 0, components);
    }
}
