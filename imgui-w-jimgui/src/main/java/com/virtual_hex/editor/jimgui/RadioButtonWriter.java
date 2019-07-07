package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.RadioButton;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeInt;

@NativeExchange
@UIComponentRegister(typeKey = RadioButton.class)
public class RadioButtonWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        RadioButton<JImStr> component = (RadioButton) uiComponent;

        NativeInt nativeValue = writer.getCachedNativeInt("value", component);
        nativeValue.modifyValue(component.value);

        for (int i = 0; i < component.stringLabels.length; i++) {
            JImStr stringLabel = component.stringLabels[i];
            boolean selected = out.radioButton(stringLabel, nativeValue, i);
            if(selected){
                nativeValue.modifyValue(i);
                component.value = i;
                // Was selected we should call a state change handler
                writer.handleStateChange(out, uiComponent, writer);
            }
        }
    }
}
