package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.DragVec4;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@ComponentRegister(typeKey = DragVec4.class)
public class DragVec4Writer extends NativeAllocComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        DragVec4<JImStr, JImVec4> component = (DragVec4) uiComponent;
        out.dragVec4(component.label, component.bounds, component.speed, component.min, component.max);
    }
}
