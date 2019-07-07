package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.TabItemDecorated;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.NativeBool;

@UIComponentRegister(typeKey = TabItemDecorated.class)
public class TabItemDecoratedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TabItemDecorated<JImStr> component = (TabItemDecorated) uiComponent;
        if (component.open) {
            NativeBool value = writer.getCachedNativeBool("open", component);
            // Not clipped or collapsed
            value.modifyValue(component.open);
            boolean visible = out.beginTabItem(component.label, value, component.flags);
            component.open = value.accessValue();
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
                JImGuiGen.endTabItem();
            }
        }
        writer.handleStateChange(out, component, writer);
    }
}
