package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.SetToolTip;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = SetToolTip.class)
public class SetToolTipWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        SetToolTip<JImStr> component = (SetToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.setTooltip(component.label);
        }
    }
}
