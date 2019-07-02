package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.CollapsingHeaderDecorated;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;

@NativeExchange
@ComponentRegister(typeKey = CollapsingHeaderDecorated.class)
public class CollapsingHeaderDecoratedWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        CollapsingHeaderDecorated<JImStr> component = (CollapsingHeaderDecorated) uiComponent;
        NativeBool value = getNative("open", component);
        // Not clipped or collapsed
        value.modifyValue(component.open);
        boolean open = out.collapsingHeader(component.label, value, component.flags);
        component.open = value.accessValue();
        if (open) { // TODO Maybe remove this open check
            UIComponentsUtils.processUiDataList(out, component, writer);
        }
        // Trigger handler only when X is pressed on collapsing header
        if (!component.open) {
            writer.handleActivation(out, component, writer);
        }
        writer.handleStateChange(out, component, writer);
    }
}
