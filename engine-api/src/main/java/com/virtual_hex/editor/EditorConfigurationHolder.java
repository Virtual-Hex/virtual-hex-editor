package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;

@Toggle(fieldName = "open")
public class EditorConfigurationHolder extends AbstractUIComponent {

    public String name;
    public EditorConfiguration editorConfiguration;
    public boolean open;

    public EditorConfigurationHolder() {
    }

    public EditorConfigurationHolder(String name, EditorConfiguration editorConfiguration) {
        this.name = name;
        this.editorConfiguration = editorConfiguration;
    }
}
