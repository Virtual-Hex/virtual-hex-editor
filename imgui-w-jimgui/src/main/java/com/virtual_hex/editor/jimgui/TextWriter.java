package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Text;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = Text.class)
public class TextWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Text<String> component = (Text<String>) uiComponent;

        Text<String> component1 =  Text.of("String");
        Text<JImStr> component2 =  Text.of(new JImStr("String"));
        out.text(component.label);
    }
}
