package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Dummy;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@ComponentRegister(typeKey = Dummy.class)
public class DummyWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Dummy component = (Dummy) uiComponent;
        JImGuiGen.dummy(component.width, component.height);

    }
}
