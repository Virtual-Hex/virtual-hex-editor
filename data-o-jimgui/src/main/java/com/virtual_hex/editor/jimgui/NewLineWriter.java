package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.NewLine;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@ComponentRegister(typeKey = NewLine.class)
public class NewLineWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        JImGuiGen.newLine();
    }

}
