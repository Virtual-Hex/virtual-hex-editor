package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ClassLoaderUIComponent;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import com.virtual_hex.editor.jimgui.JImGuiComponentWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ClassLoaderUIComponent.class)
public class ClassLoaderWrapperWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        out.text("Test Wrapper");
    }
}
