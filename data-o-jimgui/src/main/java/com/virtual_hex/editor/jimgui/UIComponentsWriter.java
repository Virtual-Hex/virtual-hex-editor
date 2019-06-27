package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = UIComponents.class)
public class UIComponentsWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        UIComponents component = (UIComponents) uiComponent;
        UIComponentsUtils.processUiDataList(out, component, writer);
    }
}