package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.DragInt;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeInt;

@ComponentRegister(typeKey = DragInt.class)
public class DragIntWriter extends NativeIntComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        DragInt<JImStr> component = (DragInt) uiComponent;

        NativeInt nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.dragInt(component.label, nativeValue, component.valueSpeed, component.valueMin, component.valueMax, component.format);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    }
}
