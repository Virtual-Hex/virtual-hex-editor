package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ColorText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@NativeExchange
@ComponentRegister(typeKey = ColorText.class)
public class ColorTextWriter extends NativeJImVec4ComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ColorText component = (ColorText) uiComponent;
        Vec4 color = component.color;
        JImVec4 jImVec4 = getCachedOrCreate(color);
        out.textColored(jImVec4, component.label);

    }
}
