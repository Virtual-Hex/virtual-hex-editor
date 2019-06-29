package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.DragFloatRange;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeFloat;

@ComponentRegister(typeKey = DragFloatRange.class)
public class DragFloatRangeWriter extends NativeFloatComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        DragFloatRange<JImStr> component = (DragFloatRange) uiComponent;

        NativeFloat nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);

        NativeFloat nativeValue2 = getNative("value2", component);
        nativeValue2.modifyValue(component.value2);

        boolean stateChanged = out.dragFloatRange2(component.label, nativeValue, nativeValue2, component.valueSpeed, component.valueMin, component.valueMax, component.format, component.format2, component.power);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            component.value2 = nativeValue2.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    }
}
