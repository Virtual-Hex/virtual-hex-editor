package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.SliderAngle;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeFloat;

@ComponentRegister(typeKey = SliderAngle.class)
public class SliderAngleWriter extends NativeFloatComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        SliderAngle component = (SliderAngle) uiComponent;
        NativeFloat nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderAngle(component.label, nativeValue, component.valueDegreeMin, component.valueDegreeMax);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
