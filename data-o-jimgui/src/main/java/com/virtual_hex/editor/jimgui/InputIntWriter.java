package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.InputInt;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeInt;

@NativeExchange
@ComponentRegister(typeKey = InputInt.class)
public class InputIntWriter extends NativeIntComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        InputInt component = (InputInt) uiComponent;
        NativeInt nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputInt(component.label, nativeValue);
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
