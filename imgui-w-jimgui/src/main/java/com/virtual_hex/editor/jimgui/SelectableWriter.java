package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.Selectable;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = Selectable.class)
public class SelectableWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Selectable<JImStr> component = (Selectable) uiComponent;
        boolean selected = out.selectable0(component.label, component.selected, component.flags, component.width, component.height);

        if (selected) {
            component.selected = !component.selected;
            writer.handleStateChange(out, component, writer);
        }
    }
}
