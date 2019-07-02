package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorEdit4;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorEdit4.class)
public class ColorEdit4Writer extends NativeAllocComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorEdit4<JImStr, JImVec4> component = (ColorEdit4) uiComponent;

        boolean stateChange = out.colorEdit4(component.label, component.color, component.flags);
        if(stateChange){
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
