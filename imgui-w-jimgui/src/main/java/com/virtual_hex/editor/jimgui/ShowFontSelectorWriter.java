package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.ShowFontSelector;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = ShowFontSelector.class)
public class ShowFontSelectorWriter extends JImGuiComponentWriter{

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ShowFontSelector<JImStr> component = (ShowFontSelector) uiComponent;
        out.showFontSelector(component.label);
    }
}
