package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Child;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Child.class)
public class ChildWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Child component = (Child) uiComponent;

        // Not clipped or collapsed
        boolean visible = out.beginChild0(component.label, component.width, component.height, component.border, component.flags);
        if (visible) {
            UIComponentsUtils.processUiDataList(out, component, writer);
        }

    }
}
