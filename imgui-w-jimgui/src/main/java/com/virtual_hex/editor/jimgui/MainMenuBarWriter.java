package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.MainMenuBar;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = MainMenuBar.class)
public class MainMenuBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        MainMenuBar component = (MainMenuBar) uiComponent;
        boolean open = out.beginMainMenuBar();
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.endMainMenuBar();
        }

    }
}
