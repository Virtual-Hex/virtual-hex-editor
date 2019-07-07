package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.InputDoubleStepped;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeDouble;

@NativeExchange
@UIComponentRegister(typeKey = InputDoubleStepped.class)
public class InputDoubleSteppedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InputDoubleStepped<String> component = (InputDoubleStepped) uiComponent;
        NativeDouble nativeValue = writer.getCachedNativeDouble("value", component);
        nativeValue.modifyValue(component.value);
        boolean fieldChanged = out.inputDouble(component.label, nativeValue, component.step, component.stepFast, component.format);
        // TODO Activation Handler or filter handler TBD
        if (fieldChanged) {
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }

    }
}
