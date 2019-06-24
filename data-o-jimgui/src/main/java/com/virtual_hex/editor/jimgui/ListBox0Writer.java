package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ListBox0;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ListBox0.class)
public class ListBox0Writer extends JImGuiComponentWriter {


    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ListBox0 component = (ListBox0) uiComponent;

        boolean draw = out.listBoxHeader0(component.label, component.width, component.height);
        if(draw){
            // TODO Into Selectables Group
            UIComponentsUtils.processUiDataList(out, component, writer);
            out.listBoxFooter();
        }
    }
}
