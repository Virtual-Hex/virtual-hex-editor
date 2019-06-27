package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.VSliderFloat;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeFloat;

@ComponentRegister(typeKey = VSliderFloat.class)
public class VSliderFloatWriter extends NativeFloatComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        VSliderFloat component = (VSliderFloat) uiComponent;
        NativeFloat nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.vSliderFloat(component.label, component.width, component.height, nativeValue, component.valueMin, component.valueMax, component.format, component.power);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
