package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Label;
import com.virtual_hex.editor.data.RadioButton0;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

// This is actually likely faster then the RadioButton due to the no native interface
@ComponentRegister(typeKey = RadioButton0.class)
public class RadioButton0Writer extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        RadioButton0 component = (RadioButton0) uiComponent;

        for (int i = 0; i < component.labels.length; i++) {
            Label label = component.labels[i];
            boolean selected = out.radioButton0(label.label, component.value == i);
            System.out.println(selected);
            if(selected){
                component.value = i;
                // Was selected we should call a state change handler
                writer.handleStateChange(out, uiComponent, writer);
            }
        }

    }
}
