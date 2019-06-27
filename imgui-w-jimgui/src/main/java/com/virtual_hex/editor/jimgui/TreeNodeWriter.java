package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.TreeNode;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIComponentsUtils;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = TreeNode.class)
public class TreeNodeWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TreeNode component = (TreeNode) uiComponent;
        boolean open = out.treeNode(component.label);
        if (open) {
            UIComponentsUtils.processUiDataList(out, component, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    }
}
