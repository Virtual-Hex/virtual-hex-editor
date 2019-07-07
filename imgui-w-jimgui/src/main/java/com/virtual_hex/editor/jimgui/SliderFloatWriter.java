package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.SliderFloat;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeFloat;

@UIComponentRegister(typeKey = SliderFloat.class)
public class SliderFloatWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        SliderFloat<JImStr> component = (SliderFloat) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.sliderFloat(component.label, nativeValue, component.valueMin, component.valueMax, component.format, component.power);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
