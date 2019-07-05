package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ShowAboutWindow;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ShowAboutWindow.class)
public class ShowAboutWindowWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowAboutWindow component = (ShowAboutWindow) uiComponent;
        if(component.open) out.showAboutWindow();
    }
}
