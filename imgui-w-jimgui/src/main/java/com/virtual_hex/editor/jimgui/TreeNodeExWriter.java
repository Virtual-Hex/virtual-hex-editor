package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TreeNodeEx;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = TreeNodeEx.class)
public class TreeNodeExWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TreeNodeEx component = (TreeNodeEx) uiComponent;
        boolean open = out.treeNodeEx(component.label, component.flags);
        if (open) {
            UIComponentsUtils.processUiDataList(out, component, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    }
}
