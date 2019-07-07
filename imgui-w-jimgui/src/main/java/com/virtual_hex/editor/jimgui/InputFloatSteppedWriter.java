package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.InputFloatStepped;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeFloat;

@NativeExchange
@UIComponentRegister(typeKey = InputFloatStepped.class)
public class InputFloatSteppedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        InputFloatStepped<JImStr> component = (InputFloatStepped) uiComponent;
        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
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
