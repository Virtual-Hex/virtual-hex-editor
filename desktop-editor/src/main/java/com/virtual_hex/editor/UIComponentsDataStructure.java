package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;
import com.virtual_hex.editor.data.UIComponent;

public class UIComponentsDataStructure extends AbstractUIComponent {

    public UIComponent[] uiComponents;

    public UIComponentsDataStructure() {
    }

    public UIComponentsDataStructure(UIComponent[] uiComponents) {
        this.uiComponents = uiComponents;
    }

}
