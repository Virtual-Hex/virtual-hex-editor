package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;
import com.virtual_hex.editor.data.UIComponent;

import java.util.List;

public class UIComponentsDataStructure extends AbstractUIComponent {

    public UIComponent[] editorRoot;
    public List<UIComponent[]> widgets;

    public UIComponentsDataStructure() {
    }

    public UIComponentsDataStructure(UIComponent[] editorRoot) {
        this.editorRoot = editorRoot;
    }

}
