package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ShowUserGuide;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ShowUserGuide.class)
public class ShowUserGuideWriter extends JImGuiComponentWriter{

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowUserGuide component = (ShowUserGuide) uiComponent;
        out.showUserGuide();
    }
}
