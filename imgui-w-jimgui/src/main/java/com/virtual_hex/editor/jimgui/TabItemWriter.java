package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TabItem;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@ComponentRegister(typeKey = TabItem.class)
public class TabItemWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TabItem component = (TabItem) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabItem(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentsUtils.processUiDataList(out, component, writer);
                JImGuiGen.endTabItem();
            }
        }
        writer.handleStateChange(out, component, writer);
    }
}
