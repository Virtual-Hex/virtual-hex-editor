package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.TabItemDecorated;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.NativeBool;

@ComponentRegister(typeKey = TabItemDecorated.class)
public class TabItemDecoratedWriter extends NativeBoolComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        TabItemDecorated component = (TabItemDecorated) uiComponent;
        if (component.open) {
            NativeBool value = getNative("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.beginTabItem(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentsWriter.processUiDataList(out, component, writer);
                JImGuiGen.endTabItem();
            }
        }
        writer.handleStateChange(out, component, writer);
    }
}
