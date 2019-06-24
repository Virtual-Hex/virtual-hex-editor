package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ColorPicker4;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorPicker4.class)
public class ColorPicker4Writer extends NativeJImVec4ComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ColorPicker4 component = (ColorPicker4) uiComponent;

        Vec4 color = component.color;
        JImVec4 jImVec4 = getCachedOrCreate(color);

        boolean stateChange = out.colorPicker4(component.label, jImVec4, component.flags);
        if(stateChange){
            setFromTo(jImVec4, color);
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
