package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.ShowStyleSelector;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = ShowStyleSelector.class)
public class ShowStyleSelectorWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowStyleSelector<JImStr> component = (ShowStyleSelector) uiComponent;
        out.showStyleSelector(component.label);
    }
}
