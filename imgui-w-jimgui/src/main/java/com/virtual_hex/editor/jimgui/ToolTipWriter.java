package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ToolTip;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ToolTip.class)
public class ToolTipWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ToolTip component = (ToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.beginTooltip();
            processArray(out, component.uiComponents, writer);
            out.endTooltip();
        }
    }
}
