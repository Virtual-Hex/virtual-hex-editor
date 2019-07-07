package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.SameLine;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@UIComponentRegister(typeKey = SameLine.class)
public class SameLineWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        SameLine component = (SameLine) uiComponent;
        JImGuiGen.sameLine(component.positionX, component.spacingWidth);

    }
}
