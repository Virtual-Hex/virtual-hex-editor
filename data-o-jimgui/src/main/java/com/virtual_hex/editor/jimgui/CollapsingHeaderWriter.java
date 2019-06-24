package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.CollapsingHeader;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = CollapsingHeader.class)
public class CollapsingHeaderWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        CollapsingHeader component = (CollapsingHeader) uiComponent;
        boolean open = out.collapsingHeader(component.label);
        if (open) { // TODO Maybe remove this open check
            UIComponentsUtils.processUiDataList(out, component, writer);
        }
        writer.handleStateChange(out, component, writer);
    }
}
