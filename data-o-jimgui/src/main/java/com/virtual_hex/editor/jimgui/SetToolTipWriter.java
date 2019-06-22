package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.SetToolTip;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = SetToolTip.class)
public class SetToolTipWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        SetToolTip component = (SetToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.setTooltip(component.label);
        }
    }
}
