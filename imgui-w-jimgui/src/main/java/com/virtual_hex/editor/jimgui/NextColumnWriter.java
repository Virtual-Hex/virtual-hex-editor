package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.NextColumn;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = NextColumn.class)
public class NextColumnWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        out.nextColumn();

    }
}
