package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Indent;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Indent.class)
public class IndentWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Indent component = (Indent) uiComponent;
        out.indent(component.value);

    }
}
