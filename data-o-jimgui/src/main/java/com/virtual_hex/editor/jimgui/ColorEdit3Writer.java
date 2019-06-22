package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ColorEdit3;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorEdit3.class)
public class ColorEdit3Writer extends NativeJImVec4ComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ColorEdit3 component = (ColorEdit3) uiComponent;

        Vec4 color = component.color;
        JImVec4 jImVec4 = cachedjimVec.computeIfAbsent(color.hashCode(), value -> create(color.x, color.y, color.z, color.w));

        boolean stateChange = out.colorEdit3(component.label, jImVec4, component.flags);
        if(stateChange){
            setFromTo(jImVec4, color);
            writer.handleStateChange(out, uiComponent, writer);
        }
    }
}
