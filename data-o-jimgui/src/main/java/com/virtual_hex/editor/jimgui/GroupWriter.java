package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Group;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Group.class)
public class GroupWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Group component = (Group) uiComponent;
        out.beginGroup();
        UIComponentsUtils.processUiDataList(out, component, writer);
        out.endGroup();
    }
}
