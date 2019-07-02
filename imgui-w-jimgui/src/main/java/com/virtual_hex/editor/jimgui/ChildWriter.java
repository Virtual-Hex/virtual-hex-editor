package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Child;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = Child.class)
public class ChildWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Child<JImStr> component = (Child) uiComponent;

        // Not clipped or collapsed
        boolean visible = out.beginChild0(component.label, component.width, component.height, component.border, component.flags);
        if (visible) {
            UIComponentsUtils.processUiDataList(out, component, writer);
        }

    }
}
