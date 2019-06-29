package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.WindowDecorated;
import org.ice1000.jimgui.JImStr;

public class ProjectsWindow extends WindowDecorated {

    public ProjectsWindow(UIComponent... components) {
        super(new JImStr("Projects"), false, 0, components);
    }
}
