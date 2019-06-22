package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Unindent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Unindent.class)
public class UnindentWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Unindent component = (Unindent) uiComponent;
        out.unindent(component.value);
    }
}
