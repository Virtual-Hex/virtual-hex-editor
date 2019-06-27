package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.MenuItem;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = MenuItem.class)
public class MenuItemWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        MenuItem component = (MenuItem) uiComponent;
        // Returns true when activated
        boolean value = out.menuItem(component.label, component.shortcut);
        if (value) {
            writer.handleActivation(out, component, writer);
        }

    }
}
