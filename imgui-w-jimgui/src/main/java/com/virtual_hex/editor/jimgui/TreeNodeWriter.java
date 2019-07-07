package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.TreeNode;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;

@UIComponentRegister(typeKey = TreeNode.class)
public class TreeNodeWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        TreeNode<JImStr> component = (TreeNode) uiComponent;
        boolean open = out.treeNode(component.label);
        if (open) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            out.treePop();
        }
        writer.handleStateChange(out, component, writer);
    }
}
