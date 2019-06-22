package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InvisibleButton;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = InvisibleButton.class)
public class InvisibleButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InvisibleButton component = (InvisibleButton) uiComponent;
        boolean pressed = out.invisibleButton(component.label, component.width, component.height);
        if (pressed) writer.handleActivation(out, component, writer);
    }
}
