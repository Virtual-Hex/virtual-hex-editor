package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = InputText.class)
public class TextInputWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputText component = (InputText) uiComponent;

        boolean fieldChanged = out.inputText(component.label, component.buffer, component.flags);
        if (fieldChanged) {
            // Handle the input if needed
            writer.handleStateChange(out, component, writer);
        }
    }
}
