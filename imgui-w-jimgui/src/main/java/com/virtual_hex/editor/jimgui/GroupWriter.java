package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Group;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Group.class)
public class GroupWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Group component = (Group) uiComponent;
        out.beginGroup();
        UIComponentsUtils.processUiDataList(out, component, writer);
        out.endGroup();
    }
}
