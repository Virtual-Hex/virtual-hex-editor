package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;
import com.virtual_hex.editor.data.UIComponent;
import io.github.classgraph.ClassInfoList;

public class UIComponentsDataStructure extends AbstractUIComponent {

    public ClassInfoList uiComponents;
    public UIComponent[] root;

    public UIComponentsDataStructure() {
    }

    public UIComponentsDataStructure(UIComponent[] root, ClassInfoList uiComponents) {
        this.root = root;
        this.uiComponents = uiComponents;
    }
}
