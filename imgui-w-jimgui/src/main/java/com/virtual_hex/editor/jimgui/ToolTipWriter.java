package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.ToolTip;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = ToolTip.class)
public class ToolTipWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ToolTip component = (ToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.beginTooltip();
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endTooltip();
        }
    }
}
