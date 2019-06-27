package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;


public class SpacingWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        JImGuiGen.spacing();
    }
}
