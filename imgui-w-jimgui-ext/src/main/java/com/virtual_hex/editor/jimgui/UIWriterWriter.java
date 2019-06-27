package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = UIWriter.class)
public class UIWriterWriter extends JImGuiComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        UIWriter uiWriter = (UIWriter) uiComponent;
        // This will be rendered inside of a window
        // Eventually will need an api to maybe auto location to abstract the logic more,
        // Where we can use a window, tree, header, popup, ect.




    }
}
