package com.virtual_hex.editor;

import com.virtual_hex.editor.data.LabeledComponents;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImStr;

public class EditorConfigCache extends LabeledComponents<JImStr> {

    public EditorConfigCache() {
    }

    public EditorConfigCache(String name, UIComponent... components) {
        super(new JImStr(name), components);
    }
}
