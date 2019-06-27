package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ColorEdit4;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorEdit4.class)
public class ColorEdit4Writer extends NativeJImVec4ComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ColorEdit4 component = (ColorEdit4) uiComponent;

        Vec4 color = component.color;
        JImVec4 jImVec4 = getCachedOrCreate(color);
        boolean stateChange = out.colorEdit4(component.label, jImVec4, component.flags);
        if(stateChange){
            setFromTo(jImVec4, color);
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
