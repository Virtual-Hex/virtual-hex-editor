package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ArrowButton;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = ArrowButton.class)
public class ArrowButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ArrowButton<JImStr> component = (ArrowButton) uiComponent;
        boolean pressed = out.arrowButton(component.label, component.direction.index);
        if (pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
