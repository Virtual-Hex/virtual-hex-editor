package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;

public class EditorConfigurationHolder extends AbstractUIComponent {

    public String name;
    public EditorConfiguration editorConfiguration;

    public EditorConfigurationHolder() {
    }

    public EditorConfigurationHolder(String name, EditorConfiguration editorConfiguration) {
        this.name = name;
        this.editorConfiguration = editorConfiguration;
    }
}
