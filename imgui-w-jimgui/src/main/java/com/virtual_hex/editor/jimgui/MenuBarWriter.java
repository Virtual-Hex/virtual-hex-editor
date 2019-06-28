package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.MenuBar;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = MenuBar.class)
public class MenuBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        MenuBar component = (MenuBar) uiComponent;
        boolean value = out.beginMenuBar();
        if (value) {
            UIComponentsUtils.processUiDataList(out, component, writer);
            out.endMenuBar();
        }

    }
}