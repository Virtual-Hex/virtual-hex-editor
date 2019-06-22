package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Text;
import com.virtual_hex.editor.data.TextUnformatted;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = TextUnformatted.class)
public class TextUnformattedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Text component = (Text) uiComponent;
        out.textUnformatted(component.label);
    }
}
