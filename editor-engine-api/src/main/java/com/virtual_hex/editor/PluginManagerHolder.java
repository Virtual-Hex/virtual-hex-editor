package com.virtual_hex.editor;

/**
 * This must be set by the application
 */
public enum PluginManagerHolder {
    INSTANCE(null);

    public PluginManager pluginManager;

    PluginManagerHolder(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    public void set(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }
}


