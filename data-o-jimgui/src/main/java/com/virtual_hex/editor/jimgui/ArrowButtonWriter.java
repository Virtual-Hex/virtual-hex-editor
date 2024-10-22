package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ArrowButton;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ArrowButton.class)
public class ArrowButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter writer) {
        ArrowButton uiComponent0 = (ArrowButton) uiComponent;
        boolean pressed = out.arrowButton(uiComponent0.label, ((ArrowButton) uiComponent).direction);
        if (pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
