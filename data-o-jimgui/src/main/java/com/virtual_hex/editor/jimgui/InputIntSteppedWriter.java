package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputIntStepped;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeInt;

@NativeExchange
@ComponentRegister(typeKey = InputIntStepped.class)
public class InputIntSteppedWriter extends NativeIntComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputIntStepped component = (InputIntStepped) uiComponent;
        NativeInt nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputInt(component.label, nativeValue, component.step, component.stepFast, component.flags);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
