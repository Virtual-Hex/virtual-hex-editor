package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.TreeNodeEx;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = TreeNodeEx.class)
public class TreeNodeExWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TreeNodeEx<JImStr> component = (TreeNodeEx) uiComponent;
        boolean open = out.treeNodeEx(component.label, component.flags);
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    }
}
