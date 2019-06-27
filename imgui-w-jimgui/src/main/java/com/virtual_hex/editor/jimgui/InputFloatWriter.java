package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.InputFloat;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeFloat;

@NativeExchange
@ComponentRegister(typeKey = InputFloat.class)
public class InputFloatWriter extends NativeFloatComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InputFloat component = (InputFloat) uiComponent;
        NativeFloat nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputFloat(component.label, nativeValue);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
