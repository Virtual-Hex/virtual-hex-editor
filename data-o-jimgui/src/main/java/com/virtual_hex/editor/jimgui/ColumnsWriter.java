package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Columns;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Columns.class)
public class ColumnsWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Columns component = (Columns) uiComponent;
        out.columns(component.count, component.stringId, component.border);
    }
}
