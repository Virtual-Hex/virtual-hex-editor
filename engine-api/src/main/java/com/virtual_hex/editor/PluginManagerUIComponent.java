package com.virtual_hex.editor;

import com.virtual_hex.editor.data.StringLabel;
import com.virtual_hex.editor.data.UIComponent;

public class PluginManagerUIComponent extends StringLabel {

    public boolean open;
    public UIComponent pluginManager;

    public PluginManagerUIComponent(String label, boolean open, UIComponent pluginManager) {
        super(label);
        this.open = open;
        this.pluginManager = pluginManager;
    }
}
