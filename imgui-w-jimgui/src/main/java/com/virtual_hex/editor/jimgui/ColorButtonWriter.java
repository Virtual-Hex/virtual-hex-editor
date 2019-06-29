package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorButton;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorButton.class)
public class ColorButtonWriter extends NativeJImVec4ComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorButton<JImStr> component = (ColorButton) uiComponent;

        Vec4 color = component.color;
        JImVec4 jImVec4 = getCachedOrCreate(color);

        boolean pressed = out.colorButton(component.label, jImVec4, component.flags, component.width, component.height);
        if(pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
