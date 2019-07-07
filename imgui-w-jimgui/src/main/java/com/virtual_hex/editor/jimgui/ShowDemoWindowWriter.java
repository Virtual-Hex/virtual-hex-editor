package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.ShowDemoWindow;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = ShowDemoWindow.class)
public class ShowDemoWindowWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowDemoWindow component = (ShowDemoWindow) uiComponent;
        if (component.open) out.showDemoWindow();
    }
}
