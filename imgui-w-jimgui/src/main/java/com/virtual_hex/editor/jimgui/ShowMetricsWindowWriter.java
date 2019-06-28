package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ShowMetricsWindow;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ShowMetricsWindow.class)
public class ShowMetricsWindowWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowMetricsWindow component = (ShowMetricsWindow) uiComponent;
        if(component.open) out.showMetricsWindow();
        writer.handleStateChange(out, component, writer);
    }
}