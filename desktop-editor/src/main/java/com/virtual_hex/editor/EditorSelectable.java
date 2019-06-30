package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Selectable;
import org.ice1000.jimgui.JImStr;

public class EditorSelectable extends Selectable<JImStr> {

    public EditorSelectable() {
    }

    public EditorSelectable(String string) {
        super(new JImStr(string));
    }

    public EditorSelectable(String string, boolean selected) {
        super(new JImStr(string), selected);
    }

    public EditorSelectable(String string, boolean selected, int flags) {
        super(new JImStr(string), selected, flags);
    }

    public EditorSelectable(String string, int width, int height, boolean selected, int flags) {
        super(new JImStr(string), width, height, selected, flags);
    }
}
