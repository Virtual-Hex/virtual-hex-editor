package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.CollapsingHeader;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = CollapsingHeader.class)
public class CollapsingHeaderWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        CollapsingHeader component = (CollapsingHeader) uiComponent;
        boolean open = out.collapsingHeader(component.label);
        if (open) {
            processArray(out, component.uiComponents, writer);
        }
        writer.handleStateChange(out, component, writer);
    }
}
