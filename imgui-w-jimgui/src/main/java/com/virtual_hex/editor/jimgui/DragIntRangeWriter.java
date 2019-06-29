package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.DragIntRange;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeInt;

@ComponentRegister(typeKey = DragIntRange.class)
public class DragIntRangeWriter extends NativeIntComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        DragIntRange<JImStr> component = (DragIntRange) uiComponent;

        NativeInt nativeValue = getNative("value", component);
        nativeValue.modifyValue(component.value);

        NativeInt nativeValue2 = getNative("value2", component);
        nativeValue2.modifyValue(component.value2);

        boolean stateChanged = out.dragIntRange2(component.label, nativeValue, nativeValue2, component.valueSpeed, component.valueMin, component.valueMax, component.format);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            component.value2 = nativeValue2.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    }
}
