package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Combo;
import com.virtual_hex.editor.data.Selectable;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

import java.util.List;

@ComponentRegister(typeKey = Combo.class)
public class ComboWriter extends JImGuiComponentWriter {

    // TODO SORTING ?

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Combo<JImStr> component = (Combo) uiComponent;

        boolean selected = out.beginCombo(component.label, component.currentSelectable.label, component.flags);
        if (selected) {
            UIComponents uiComponents = component;
            UIComponentsUtils.processAddRemoveComponent(uiComponents);
            // Loop through selectables here
            List<UIComponent> components = component.uiComponents;
            for (UIComponent uiSelectable : components) {
                Selectable<JImStr> selectable = (Selectable) uiSelectable;
                boolean isSelected = selectable.equals(component.currentSelectable);
                if (out.selectable0(selectable.label, isSelected, selectable.flags, selectable.width, selectable.height)) {
                    component.currentSelectable = selectable;
                }
                if (selected) out.setItemDefaultFocus();
            }
            // TODO Before after combo..
            writer.handleStateChange(out, uiComponent, writer);
            out.endCombo();
        }

    }
}