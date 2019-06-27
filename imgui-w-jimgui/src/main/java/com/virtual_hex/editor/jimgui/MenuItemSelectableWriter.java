package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.MenuItemSelectable;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

@ComponentRegister(typeKey = MenuItemSelectable.class)
public class MenuItemSelectableWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        MenuItemSelectable component = (MenuItemSelectable) uiComponent;
        NativeBool value = getNative("selected", component);

        value.modifyValue(component.selected);
        boolean activated = out.menuItem(component.label, component.shortcut, value, component.enabled);

        // Returns true when activated
        if (activated) {
            component.selected = value.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
