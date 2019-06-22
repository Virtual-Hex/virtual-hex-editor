package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.LabelText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = LabelText.class)
public class LabelTextWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        LabelText component = (LabelText) uiComponent;
        out.labelText(component.label, component.text);
    }
}
