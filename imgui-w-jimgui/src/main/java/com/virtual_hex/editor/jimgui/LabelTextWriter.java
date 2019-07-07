package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.LabelText;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

// See StringLabelWriter for sample of the two types, ones more efficient then the other which is the JImStr
@UIComponentRegister(typeKey = LabelText.class)
public class LabelTextWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        LabelText<JImStr> component = (LabelText) uiComponent;
        out.labelText(component.label, component.text);
    }
}
