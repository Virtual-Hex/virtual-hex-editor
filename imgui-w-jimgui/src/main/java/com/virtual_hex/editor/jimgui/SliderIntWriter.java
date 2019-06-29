package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.SliderInt;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeInt;

@ComponentRegister(typeKey = SliderInt.class)
public class SliderIntWriter extends NativeIntComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        SliderInt<JImStr> component = (SliderInt) uiComponent;
        NativeInt nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderInt(component.label, nativeValue, component.valueMin, component.valueMax, component.format);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
