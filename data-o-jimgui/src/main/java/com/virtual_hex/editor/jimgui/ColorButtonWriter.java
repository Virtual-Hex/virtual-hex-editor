package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ColorButton;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = ColorButton.class)
public class ColorButtonWriter extends NativeJImVec4ComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ColorButton component = (ColorButton) uiComponent;

        Vec4 color = component.color;
        JImVec4 jImVec4 = cachedjimVec.computeIfAbsent(color.hashCode(), value -> create(color.x, color.y, color.z, color.w));

        boolean pressed = out.colorButton(component.label, jImVec4, component.flags, component.width, component.height);
        if(pressed) writer.handleActivation(out, uiComponent, writer);
    }
}
