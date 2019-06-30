package com.virtual_hex.editor;

import com.virtual_hex.editor.data.ListBox;
import com.virtual_hex.editor.data.Selectable;
import org.ice1000.jimgui.JImStr;

public class EditorListBox extends ListBox<JImStr> {

    public EditorListBox() {
    }

    public EditorListBox(String string, int heightInItems, Selectable<JImStr>... components) {
        super(new JImStr(string), heightInItems, components);
    }

    public EditorListBox(String string, int itemsCount, int heightInItems, Selectable<JImStr>... components) {
        super(new JImStr(string), itemsCount, heightInItems, components);
    }
}
