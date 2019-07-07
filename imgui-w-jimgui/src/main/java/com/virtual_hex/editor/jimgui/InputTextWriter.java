package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.InputText;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = InputText.class)
public class InputTextWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InputText component = (InputText) uiComponent;

        boolean fieldChanged = out.inputText(component.label, component.buffer, component.flags);
        if (fieldChanged) {
            // Handle the input if needed
            writer.handleStateChange(out, component, writer);
        }
    }
}
