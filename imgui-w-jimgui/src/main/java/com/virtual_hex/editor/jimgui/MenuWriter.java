package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Menu;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = Menu.class)
public class MenuWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Menu<JImStr> component = (Menu) uiComponent;
        boolean open = out.beginMenu(component.label, component.enabled);
        if (open) {
            processArray(out, component.uiComponents, writer);
            out.endMenu();
        }

    }
}
