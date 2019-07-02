package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorButton;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorButton.class)
public class ColorButtonWriter extends NativeAllocComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorButton<JImStr, JImVec4> component = (ColorButton) uiComponent;
        boolean pressed = out.colorButton(component.label, component.color, component.flags, component.width, component.height);
        if(pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
