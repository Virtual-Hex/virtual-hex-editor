package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.ListBox;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = ListBox.class)
public class ListBoxWriter extends JImGuiComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ListBox<JImStr> component = (ListBox) uiComponent;

        boolean draw = out.listBoxHeader(component.label, component.itemsCount, component.heightInItems);
        if(draw){
            // TODO Into Selectables Group
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.listBoxFooter();
        }
    }


}
