package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.CheckBox;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

@NativeExchange
@ComponentRegister(typeKey = CheckBox.class)
public class CheckBoxWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        CheckBox component = (CheckBox) uiComponent;

        // getting a Boolean
        NativeBool value = getNative("checked", component);
        // Modify it to reflect the components value
        value.modifyValue(component.checked);
        // Write
        boolean changed = out.checkbox(component.label, value);
        if(changed){
            component.checked = value.accessValue();
            // Been modified notify
            writer.handleStateChange(out, component, writer);
        }
    }
}
