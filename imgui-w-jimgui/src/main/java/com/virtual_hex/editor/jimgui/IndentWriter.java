package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.Indent;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = Indent.class)
public class IndentWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Indent component = (Indent) uiComponent;
        out.indent(component.value);

    }
}
