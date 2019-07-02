package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorPicker3;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorPicker3.class)
public class ColorPicker3Writer extends NativeAllocComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorPicker3<JImStr, JImVec4> component = (ColorPicker3) uiComponent;

        boolean stateChange = out.colorPicker3(component.label, component.color, component.flags);
        if(stateChange){
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
