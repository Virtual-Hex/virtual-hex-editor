package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorPicker4;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorPicker4.class)
public class ColorPicker4Writer extends NativeJImVec4ComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorPicker4<JImStr, JImVec4> component = (ColorPicker4) uiComponent;

        boolean stateChange = out.colorPicker4(component.label, component.color, component.flags);
        if(stateChange){
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
