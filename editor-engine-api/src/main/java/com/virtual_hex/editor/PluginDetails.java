package com.virtual_hex.editor;




import java.util.Arrays;


/**
 * This class is to enhance a plugin manifest by converting the string versions of the plugin details into usable classes
 */
public class PluginDetails {

    private final PluginManifest pluginManifest;
    private final int version;
    private final String[] dependencies;

    public PluginDetails(PluginManifest pluginManifest, int version, String[] dependencies) {
        this.pluginManifest = pluginManifest;
        this.version = version;
        this.dependencies = dependencies;
    }

    @Override
    public String toString() {
        return "PluginDetails{" +
                "pluginManifest=" + pluginManifest +
                ", version=" + version +
                ", dependencies=" + Arrays.toString(dependencies) +
                '}';
    }

    public PluginManifest getPluginManifest() {
        return pluginManifest;
    }

    public int getVersion() {
        return version;
    }

    public String[] getDependencies() {
        return dependencies;
    }
}
