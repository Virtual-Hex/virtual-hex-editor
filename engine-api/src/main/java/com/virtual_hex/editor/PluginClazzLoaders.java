package com.virtual_hex.editor;

import java.util.concurrent.ConcurrentHashMap;

public class PluginClazzLoaders {
    
    private static final PluginClazzLoaders INSTANCE = new PluginClazzLoaders();
    
    public static PluginClazzLoaders getINSTANCE() {
        return INSTANCE;
    }
    
    private PluginClazzLoaders() {
        this.pluginClassLoaders = new ConcurrentHashMap<>(16, 1f, 1);
    }
    
    /* End Singleton Details */

    protected final ConcurrentHashMap<String, PluginClazzLoader> pluginClassLoaders;

    /**
     * Getter for property 'pluginClassLoaders'.
     *
     * @return Value for property 'pluginClassLoaders'.
     */
    public ConcurrentHashMap<String, PluginClazzLoader> getPluginClassLoaders() {
        return pluginClassLoaders;
    }

    public void addPluginClassLoader(final String name, final PluginClazzLoader pluginClazzLoader){
        pluginClassLoaders.put(name, pluginClazzLoader);
    }

    public PluginClazzLoader removePluginClassLoader(final String name) {
        return pluginClassLoaders.remove(name);
    }
}
