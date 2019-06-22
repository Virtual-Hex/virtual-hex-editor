package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.MainMenuBar;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = MainMenuBar.class)
public class MainMenuBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        MainMenuBar component = (MainMenuBar) uiComponent;
        boolean open = out.beginMainMenuBar();
        if (open) {
            UIComponentsWriter.processUiDataList(out, component, writer);
            out.endMainMenuBar();
        }

    }
}
