package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ToolTip;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ToolTip.class)
public class ToolTipWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ToolTip component = (ToolTip) uiComponent;
        if(out.isItemHovered()) {
            out.beginTooltip();
            UIComponentsWriter.processUiDataList(out, component, writer);
            out.endTooltip();
        }
    }
}
