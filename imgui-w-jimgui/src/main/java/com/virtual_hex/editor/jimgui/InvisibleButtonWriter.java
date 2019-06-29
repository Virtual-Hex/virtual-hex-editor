package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.InvisibleButton;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = InvisibleButton.class)
public class InvisibleButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InvisibleButton<JImStr> component = (InvisibleButton) uiComponent;
        boolean pressed = out.invisibleButton(component.label, component.width, component.height);
        if (pressed) writer.handleActivation(out, component, writer);
    }
}
