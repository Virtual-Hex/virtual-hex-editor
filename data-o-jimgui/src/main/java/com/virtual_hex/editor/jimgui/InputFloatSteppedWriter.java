package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputFloatStepped;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeFloat;

@NativeExchange
@ComponentRegister(typeKey = InputFloatStepped.class)
public class InputFloatSteppedWriter extends NativeFloatComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputFloatStepped component = (InputFloatStepped) uiComponent;
        NativeFloat nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputFloat(component.label, nativeValue, component.step, component.stepFast, component.format, component.flags);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
