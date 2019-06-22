package com.virtual_hex.editor;

/**
 * TODO
 * Unpacking
 * Saving files
 */
public interface Plugin {

    /**
     * Is called when plugin is being started
     * @throws Exception that can be caused by the plugin start up. This will prevent the plugin from loading
     * if thrown
     * @return boolean if plugin can load; used to initialize needed configurations or data on disk
     */
    boolean onEnable() throws Exception;

    /**
     * Is called when plugin is being stopped
     */
    void onDisable();

    // internal methods

    /**
     * Used to set resources that the plugin can use optionally. Must override this method and use PluginWrapper.
     * This is called before onEnable(); You can use this method to also extract resources to the Plugin Data
     * Directory.
     *
     * @param pluginWrapper PluginWrapper with application resources
     */
    default void setPluginWrapper(PluginWrapper pluginWrapper){

    }
}

