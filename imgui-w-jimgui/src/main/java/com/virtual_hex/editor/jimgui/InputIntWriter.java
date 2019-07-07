package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.InputInt;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeInt;

@NativeExchange
@UIComponentRegister(typeKey = InputInt.class)
public class InputIntWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InputInt<String> component = (InputInt) uiComponent;
        NativeInt nativeValue =writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.inputInt(component.label, nativeValue);
        if (stateChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
