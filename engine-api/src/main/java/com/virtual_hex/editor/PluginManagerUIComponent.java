package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Label;
import com.virtual_hex.editor.data.UIComponent;

public class PluginManagerUIComponent extends Label {

    public boolean open;
    public UIComponent pluginManager;

    public PluginManagerUIComponent(String label, boolean open, UIComponent pluginManager) {
        super(label);
        this.open = open;
        this.pluginManager = pluginManager;
    }
}
