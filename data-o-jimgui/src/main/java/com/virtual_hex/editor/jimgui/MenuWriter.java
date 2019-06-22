package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Menu;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = Menu.class)
public class MenuWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Menu component = (Menu) uiComponent;
        boolean open = out.beginMenu(component.label, component.enabled);
        if (open) {
            UIComponentsWriter.processUiDataList(out, component, writer);
            out.endMenu();
        }

    }
}
