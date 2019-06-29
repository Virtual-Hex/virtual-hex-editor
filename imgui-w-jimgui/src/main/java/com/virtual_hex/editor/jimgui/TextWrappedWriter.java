package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TextWrapped;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = TextWrapped.class)
public class TextWrappedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TextWrapped<JImStr> component = (TextWrapped) uiComponent;
        out.textWrapped(component.label);
    }
}
