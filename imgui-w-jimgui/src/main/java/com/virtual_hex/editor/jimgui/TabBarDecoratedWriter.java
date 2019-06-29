package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TabBarDecorated;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = TabBarDecorated.class)
public class TabBarDecoratedWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TabBarDecorated<JImStr> component = (TabBarDecorated) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.beginTabBar(component.label, component.flags);
            if (visible) { // TODO Maybe remove this open check
                UIComponentsUtils.processUiDataList(out, component, writer);
                JImGuiGen.endTabBar();
            }
        }
    }
}
