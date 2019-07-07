package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.RadioButton0;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

// This is actually likely faster then the RadioButton due to the no native interface
@UIComponentRegister(typeKey = RadioButton0.class)
public class RadioButton0Writer extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        RadioButton0<JImStr> component = (RadioButton0) uiComponent;

        for (int i = 0; i < component.stringLabels.length; i++) {
            JImStr stringLabel = component.stringLabels[i];
            boolean selected = out.radioButton0(stringLabel, component.value == i);
            System.out.println(selected);
            if(selected){
                component.value = i;
                // Was selected we should call a state change handler
                writer.handleStateChange(out, uiComponent, writer);
            }
        }

    }
}
