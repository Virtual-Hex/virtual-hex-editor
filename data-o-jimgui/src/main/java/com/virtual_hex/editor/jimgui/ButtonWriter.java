package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Button;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Button.class)
public class ButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter writer) {
        Button uiComponent0 = (Button) uiComponent;
        boolean pressed = out.button(uiComponent0.label, uiComponent0.width, uiComponent0.height);
        if (pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
