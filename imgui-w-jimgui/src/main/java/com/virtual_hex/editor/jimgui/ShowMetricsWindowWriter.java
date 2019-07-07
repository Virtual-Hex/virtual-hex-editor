package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.ShowMetricsWindow;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = ShowMetricsWindow.class)
public class ShowMetricsWindowWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowMetricsWindow component = (ShowMetricsWindow) uiComponent;
        if(component.open) out.showMetricsWindow();
    }
}
