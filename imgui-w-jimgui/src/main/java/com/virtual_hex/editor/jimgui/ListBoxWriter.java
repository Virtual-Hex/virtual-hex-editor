package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ListBox;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@ComponentRegister(typeKey = ListBox.class)
public class ListBoxWriter extends JImGuiComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ListBox<JImStr> component = (ListBox) uiComponent;

        boolean draw = out.listBoxHeader(component.label, component.itemsCount, component.heightInItems);
        if(draw){
            // TODO Into Selectables Group
            UIComponentsUtils.processUiDataList(out, component, writer);
            out.listBoxFooter();
        }
    }
}
