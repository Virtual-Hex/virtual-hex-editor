package com.virtual_hex.editor;

import java.nio.file.Path;
import java.util.jar.JarFile;

public class PluginWrapper {

    /**
     * The Plugin Manager which is managing this plugin
     */
    private final PluginManager pluginManager;

    /**
     * Plugin class loader used to load this plugin and its dependencies
     */
    private final PluginClazzLoader pluginClazzLoader;

    /**
     * The Plugin Class
     */
    private final Plugin plugin;

    /**
     * The Plugins details
     */
    private final PluginDetails pluginDetails;

    /**
     * The path to the directory where the plugin is located and running from
     */
    private final Path pluginDirectory;

    /**
     * The path to the plugin file
     */
    private final Path pluginFile;

    /**
     * Where the plugins extracted files and working directory is
     * is a safe place to store files by the plugin developer
     */
    private final Path pluginsDataDirectory;

    /**
     * The Plugins Jar File
     */
    private final JarFile pluginJarFile;

    /**
     * The size of the plugin
     */
    private final long pluginSize;

    public PluginWrapper(PluginManager pluginManager,
                         PluginClazzLoader pluginClazzLoader,
                         Plugin plugin,
                         PluginDetails pluginDetails,
                         Path pluginDirectory,
                         Path pluginFile,
                         Path pluginDataDirectory,
                         JarFile pluginJarFile,
                         long pluginSize) {
        this.pluginManager = pluginManager;
        this.pluginClazzLoader = pluginClazzLoader;
        this.plugin = plugin;
        this.pluginDetails = pluginDetails;
        this.pluginDirectory = pluginDirectory;
        this.pluginFile = pluginFile;
        this.pluginsDataDirectory = pluginDataDirectory;
        this.pluginJarFile = pluginJarFile;
        this.pluginSize = pluginSize;
    }


    public PluginManager getPluginManager() {
        return pluginManager;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public PluginDetails getPluginDetails() {
        return pluginDetails;
    }

    public Path getPluginDirectory() {
        return pluginDirectory;
    }

    public Path getPluginFile() {
        return pluginFile;
    }

    public Path getPluginsDataDirectory() {
        return pluginsDataDirectory;
    }

    public JarFile getPluginJarFile() {
        return pluginJarFile;
    }

    public long getPluginSize() {
        return pluginSize;
    }

    @Override
    public String toString() {
        return "PluginWrapper{" +
                "pluginManager=" + pluginManager +
                ", plugin=" + plugin +
                ", pluginDetails=" + pluginDetails +
                ", pluginDirectory=" + pluginDirectory +
                ", pluginFile=" + pluginFile +
                ", pluginsDataDirectory=" + pluginsDataDirectory +
                ", pluginJarFile=" + pluginJarFile +
                ", pluginSize=" + pluginSize +
                '}';
    }
}
