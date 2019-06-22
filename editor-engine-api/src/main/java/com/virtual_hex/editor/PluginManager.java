package com.virtual_hex.editor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public interface PluginManager {

    List<PluginHolder> loadPlugins(File directory) throws IOException;

    List<PluginHolder> loadPlugins(Path directory) throws IOException;

    /**
     *
     * @param files File files that should be loaded as plugins
     * @return List the list of plugins
     */
    List<PluginHolder> loadPlugins(File... files);

    // Insert Directory Load

    PluginHolder loadPlugin(File file);


    PluginHolder startPlugin(PluginHolder pluginHolder);

    /**
     *
     * @param name String name of the plugin to be retrieved
     * @return Plugin representing the desired plugin
     */
    PluginHolder getPlugin(String name);

    /**
     *
     * @return List returns a list of plugins
     */
    Collection<PluginHolder> getPlugins();

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

    <T> T getExtraResource(String name);

    void addExtraResource(String name, Object resources);

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

