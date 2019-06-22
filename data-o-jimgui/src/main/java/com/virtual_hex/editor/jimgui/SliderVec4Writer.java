package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.SliderVec4;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Vec4;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;

@NativeExchange
@ComponentRegister(typeKey = SliderVec4.class)
public class SliderVec4Writer extends NativeJImVec4ComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        SliderVec4 component = (SliderVec4) uiComponent;
        Vec4 vec4 = component.vec4;
        JImVec4 jImVec4 = cachedjimVec.computeIfAbsent(vec4.hashCode(), value -> create(vec4.x, vec4.y, vec4.z, vec4.w));
        out.sliderVec4(component.label, jImVec4, component.min, component.max);
        // Set the color
        vec4.x = jImVec4.getX();
        vec4.y = jImVec4.getY();
        vec4.z = jImVec4.getZ();
        vec4.w = jImVec4.getW();

        writer.handleStateChange(out, component, writer);

    }
}
