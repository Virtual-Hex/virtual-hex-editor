package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TextString;
import com.virtual_hex.editor.data.TextUnformatted;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = TextUnformatted.class)
public class TextUnformattedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TextString component = (TextString) uiComponent;
        out.textUnformatted(component.label);
    }
}
