package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputDoubleStepped;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeDouble;

@NativeExchange
@ComponentRegister(typeKey = InputDoubleStepped.class)
public class InputDoubleSteppedWriter extends NativeDoubleComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputDoubleStepped component = (InputDoubleStepped) uiComponent;
        NativeDouble nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputDouble(component.label, nativeValue);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
