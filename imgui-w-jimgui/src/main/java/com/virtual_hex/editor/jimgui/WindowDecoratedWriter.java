package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.WindowDecorated;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;

@NativeExchange
@UIComponentRegister(typeKey = WindowDecorated.class)
public class WindowDecoratedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        WindowDecorated<JImStr> component = (WindowDecorated) uiComponent;
        if (component.open) {
            NativeBool value = writer.getCachedNativeBool("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.begin(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
            }
            JImGuiGen.end();
        }
    }
}
