package com.virtual_hex.editor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface PluginManager {
    /**
     *
     * @param directory directory to scan for plugins
     * @return List of all the PluginHolder
     * @throws IOException
     */
    List<PluginHolder> loadPlugins(File directory) throws IOException;

    /**
     *
     * @param directory directory to scan for plugins
     * @return List of all the PluginHolder
     * @throws IOException
     */
    List<PluginHolder> loadPlugins(Path directory) throws IOException;

    /**
     *
     * @param files list of Files to load as plugins
     * @return List of all the PluginHolder
     */
    List<PluginHolder> loadPlugins(File... files);

    /**
     *
     * @param files list of Files to load as plugins
     * @return List of all the PluginHolder
     */
    List<PluginHolder> loadPlugins(Path... files);

    PluginHolder loadPlugin(Path file);

    PluginHolder loadPlugin(File file);

    PluginHolder startPlugin(PluginHolder pluginHolder);

    PluginHolder[] startPlugins(PluginHolder... pluginHolders);

    PluginHolder stopPlugin(PluginHolder pluginHolder);

    PluginHolder[] stopPlugins(PluginHolder... pluginHolders);

    PluginHolder unloadPlugin(PluginHolder pluginHolder);

    PluginHolder[] unloadPlugins(PluginHolder... pluginHolders);

    /**
     *
     * @param name String name of the plugin to be retrieved
     * @return Plugin representing the desired plugin
     */
    PluginHolder getPlugin(String name);

    
    Map<String, PluginHolder> getPlugins();

    /**
     *
     * @param filename String adds a filename to block from loading
     */
    void addToBlacklist(String filename);

    /**
     *
     * @param filename String removes a file name from the blocked from loading list
     */
    void removeFromBlacklist(String filename);

    /**
     *
     * @param name String name of the value
     * @param object Object to check for registration
     * @return boolean true if registered
     */
    boolean isRegistered(String name, Object object);

    /**
     *
     * @param name String name of the value
     * @param object Object to submit for registration
     * @return boolean true if registered
     */
    boolean registered(String name, Object object);


}

