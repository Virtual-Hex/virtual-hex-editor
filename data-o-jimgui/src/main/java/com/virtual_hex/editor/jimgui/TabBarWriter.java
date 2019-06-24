package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.TabBar;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@ComponentRegister(typeKey = TabBar.class)
public class TabBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        TabBar component = (TabBar) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabBar(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentsUtils.processUiDataList(out, component, writer);
                JImGuiGen.endTabBar();
            }
        }

    }
}
