package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.MenuItemSelectable;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;

@UIComponentRegister(typeKey = MenuItemSelectable.class)
public class MenuItemSelectableWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        MenuItemSelectable<JImStr> component = (MenuItemSelectable) uiComponent;
        NativeBool value = writer.getCachedNativeBool("selected", component);

        value.modifyValue(component.selected);
        boolean activated = out.menuItem(component.label, component.shortcut, value, component.enabled);

        // Returns true when activated
        if (activated) {
            component.selected = value.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
