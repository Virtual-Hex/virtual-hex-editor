package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.WindowDecorated;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;

@NativeExchange
@ComponentRegister(typeKey = WindowDecorated.class)
public class WindowDecoratedWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        WindowDecorated<JImStr> component = (WindowDecorated) uiComponent;
        if (component.open) {
            NativeBool value = getNative("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.begin(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentsUtils.processUiDataList(out, component, writer);
            }
            JImGuiGen.end();
        }
        writer.handleStateChange(out, component, writer);
    }
}
