package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.DragFloat;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeFloat;

@UIComponentRegister(typeKey = DragFloat.class)
public class DragFloatWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        DragFloat<JImStr> component = (DragFloat) uiComponent;

        NativeFloat nativeValue = writer.getCachedNativeFloat("value", component);
        nativeValue.modifyValue(component.value);
        boolean stateChanged = out.dragFloat(component.label, nativeValue, component.valueSpeed, component.valueMin, component.valueMax, component.format, component.power);
        if(stateChanged){
            // Only set id valid, will use filter handler
            component.value = nativeValue.accessValue();
            writer.handleStateChange(out, component, writer);
        }
    }
}
