package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.Group;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = Group.class)
public class GroupWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Group component = (Group) uiComponent;
        out.beginGroup();
        UIComponentWriter.processArray(out, component.uiComponents, writer);
        out.endGroup();
    }
}
