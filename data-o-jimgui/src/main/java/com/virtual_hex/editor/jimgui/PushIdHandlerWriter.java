package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.PushId;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = PushId.class)
public class PushIdHandlerWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        PushId component = (PushId) uiComponent;
        out.pushID(component.label);
    }
}
