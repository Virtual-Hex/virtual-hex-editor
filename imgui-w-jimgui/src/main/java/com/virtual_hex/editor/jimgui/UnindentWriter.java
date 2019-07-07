package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Unindent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = Unindent.class)
public class UnindentWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Unindent component = (Unindent) uiComponent;
        out.unindent(component.value);
    }
}
