package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorText;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@NativeExchange
@ComponentRegister(typeKey = ColorText.class)
public class ColorTextWriter extends NativeJImVec4ComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorText<JImVec4> component = (ColorText) uiComponent;
        out.textColored(component.color, component.label);
    }
}
