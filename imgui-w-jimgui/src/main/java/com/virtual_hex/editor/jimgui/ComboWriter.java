package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Combo;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Combo.class)
public class ComboWriter extends JImGuiComponentWriter {

    // TODO SORTING ?

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Combo<String> component = (Combo) uiComponent;
//
//        boolean selected = out.combo(component.label, component.currentSelectable.label, component.flags);
//        if (selected) {
//            UIComponents uiComponents = component;
//            UIComponentsUtils.processAddRemoveComponent(uiComponents);
//            // Loop through selectables here
//            List<UIComponent> components = component.uiComponents;
//            for (UIComponent uiSelectable : components) {
//                Selectable selectable = (Selectable) uiSelectable;
//                boolean isSelected = selectable.equals(component.currentSelectable);
//                if (out.selectable0(selectable.label, isSelected, selectable.flags, selectable.width, selectable.height)) {
//                    component.currentSelectable = selectable;
//                }
//                if (selected) out.setItemDefaultFocus();
//            }
//            // TODO Before after combo..
//            writer.handleStateChange(out, uiComponent, writer);
//            out.endCombo();
//        }

    }
}