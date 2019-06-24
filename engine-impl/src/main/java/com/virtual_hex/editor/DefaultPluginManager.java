package com.virtual_hex.editor;


import com.virtual_hex.editor.utils.AnnotationUtils;
import com.virtual_hex.editor.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static java.util.stream.Collectors.toList;

public class DefaultPluginManager implements PluginManager {

    /**
     * Simply a logger reference
     */
    private static final Logger L = LoggerFactory.getLogger(DefaultPluginManager.class);

    private final Class<?> parentClazz;
    private final Path pluginDataPath;
    private final String target;
    private final String targetLower;
    private final Map<String, PluginHolder> plugins;
    private final Map<String, Object> extraResources;

    public DefaultPluginManager(Class parentClazz, Path pluginDataPath, String target) {
        this.parentClazz = parentClazz;
        this.pluginDataPath = pluginDataPath;
        this.target = target;
        this.targetLower = target.toLowerCase();
        // TODO
        this.plugins = new ConcurrentHashMap<>(16, 0.75f,1);
        extraResources = new ConcurrentHashMap<>(8, 1.0f, 1);
    }

    @Override
    public List<PluginHolder> loadPlugins(File directory) throws IOException {
        return loadPlugins(directory.getAbsoluteFile().toPath());
    }

    @Override
    public List<PluginHolder> loadPlugins(Path directory) throws IOException {
        List<File> filesList = Files.walk(directory)
                .filter(s -> s.toString().endsWith(".jar"))
                .map(Path::toFile)
                .collect(toList());
        File[] files = new File[filesList.size()];
        return loadPlugins(filesList.toArray(files));
    }



    @Override
    public List<PluginHolder> loadPlugins(File... files) {
        int length = files.length;
        List<PluginHolder> plugins = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            File file = files[i];
            PluginHolder pluginHolder = loadPlugin(file);
            plugins.add(pluginHolder);
        }
        return plugins;
    }



    @Override
    public PluginHolder loadPlugin(File file){
        // TODO Plugin Manager Name and Application for Debugging
        PluginLoadCode pl;


        final String pluginFileString = file.toString();

        L.trace("Plugin Manager: Loading plugin from location {}", pluginFileString);

        JarFile pluginJarFile;
        try {
            pluginJarFile = new JarFile(file);
        } catch (IOException e) {
            pl = PluginLoadCode.JAR_FILE_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        Manifest pluginJarManifest;
        try {
            pluginJarManifest = pluginJarFile.getManifest();
        } catch (IOException e) {
            pl = PluginLoadCode.JAR_MANIFEST_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        if (pluginJarManifest == null) {
            pl = PluginLoadCode.NO_JAR_MANIFEST;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "META-INF/MANIFEST.MF");
        }

        final String pluginClassString = pluginJarManifest.getMainAttributes().getValue("Plugin-Class");
        if (pluginClassString == null) {
            pl = PluginLoadCode.NO_JAR_MANIFEST_VALUE;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Plugin-Class");
        }

        String entryNameTemp = pluginClassString.replace(".", "/");
        String entryName = entryNameTemp.endsWith(".class") ? entryNameTemp : entryNameTemp + ".class";
        JarEntry jarEntry = pluginJarFile.getJarEntry(entryName);
        if (jarEntry == null) {
            pl = PluginLoadCode.CANNOT_FIND_CLASS_JAR_ENTRY;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, entryName);
        }

        PluginClazzLoader pluginClazzLoader = null;
        try {
            pluginClazzLoader = new PluginClazzLoader(new URL[]{file.toURI().toURL()});
        } catch (MalformedURLException e) {
            pl = PluginLoadCode.MALFORMED_URL;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        Class<?> pluginClazz = null;
        try {
            pluginClazz = pluginClazzLoader.loadClass(pluginClassString);
        } catch (ClassNotFoundException e) {
            pl = PluginLoadCode.CANNOT_FIND_CLASS_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        if (!Plugin.class.isAssignableFrom(pluginClazz)) {
            pl = PluginLoadCode.CLASS_PLUGIN_INHERITANCE;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Must inherit interface \"Plugin\"");
        }

        PluginManifest pluginManifest = pluginClazz.getAnnotation(PluginManifest.class);
        if(pluginManifest == null){
            pl = PluginLoadCode.MISSING_ANNOTATION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Plugin class is missing the annotation \"PluginManifest\"");
        }

        String[] targets = pluginManifest.targets();
        if (targets.length == 0) {
            pl = PluginLoadCode.NO_TARGET;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "The plugin must designate a target");
        }

        boolean targetMatch = false;
        int targetsLength = targets.length;
        for (int i = 0; i < targetsLength; i++) {
            String target = targets[i];
            if(target != null){
                if(!Objects.equals(this.targetLower, target.toLowerCase())){
                    targetMatch = true;
                    break;
                }
            }
        }

        if (!targetMatch) {
            pl = PluginLoadCode.TARGET_MISS_MATCH;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "The target for this plugin does not match this application");
        }

        List<Member> required = AnnotationUtils.getPresent(PluginManifest.class, Required.class, ElementType.METHOD);
        String[] requiredStrings = AnnotationUtils.convertToString(required);
        Method[] pluginDetailMethods = pluginManifest.getClass().getDeclaredMethods();

        for (Method pluginDetailsMethod : pluginDetailMethods) {
            String methodNameString = pluginDetailsMethod.getName();
            int i = Arrays.binarySearch(requiredStrings, methodNameString);
            boolean contains = i > 0;
            if(contains){
                boolean nullOrEmpty = false;// TODO INVOKE
                try {
                    nullOrEmpty = ReflectionUtils.isNullOrEmpty(pluginManifest, pluginDetailsMethod);
                } catch (IllegalAccessException e) {
                    pl = PluginLoadCode.ANNOTATION_METHOD_ILLEGAL_ACCESS_EXCEPTION;
                    L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
                    return new PluginHolder(pl, pluginFileString, e.getMessage());
                } catch (InvocationTargetException e) {
                    pl = PluginLoadCode.ANNOTATION_METHOD_INVOCATION_TARGET_EXCEPTION;
                    L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
                    return new PluginHolder(pl, pluginFileString, e.getMessage());
                }
                if(nullOrEmpty) {
                    pl = PluginLoadCode.MISSING_ANNOTATION_VALUE;
                    L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
                    return new PluginHolder(pl, pluginFileString, pluginDetailsMethod.getName());
                }
            }
        }

        String versionString = pluginManifest.version();
        String[] dependenciesStringArray = pluginManifest.dependencies();

        int version = Integer.parseInt(versionString);


        // TODO Dependency check
        // TODO Version check

        Plugin plugin;
        try {
            plugin = (Plugin) pluginClazz.newInstance();
        } catch (InstantiationException e) {
            pl = PluginLoadCode.PLUGIN_INSTANTIATION_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        } catch (IllegalAccessException e) {
            pl = PluginLoadCode.PLUGIN_ILLEGAL_ACCESS_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        PluginDetails pluginDetails = new PluginDetails(pluginManifest, version, dependenciesStringArray);
        Path pluginFilePath = Paths.get(pluginFileString);
        long pluginSize;
        try {
            pluginSize = Files.size(pluginFilePath);
        } catch (IOException e) {
            pl = PluginLoadCode.PLUGIN_FILE_SIZE_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }
        Path runningPluginDirectory = pluginFilePath.getParent();

        Path pluginDataDirectory = pluginDataPath.resolve(pluginManifest.name());
        try {
            Files.createDirectories(pluginDataDirectory);
        } catch (IOException e) {
            pl = PluginLoadCode.PLUGIN_DATA_DIRECTORY_CREATE_ERROR;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        PluginWrapper pluginWrapper = new PluginWrapper(
                this,
                pluginClazzLoader, plugin,
                pluginDetails,
                runningPluginDirectory,
                pluginFilePath,
                pluginDataDirectory,
                pluginJarFile,
                pluginSize);
        plugin.setPluginWrapper(pluginWrapper);

        pl = PluginLoadCode.SUCCESS_LOAD;
        return new PluginHolder(pl, pluginFileString, "Success Load");
    }

    @Override
    public PluginHolder startPlugin(PluginHolder pluginHolder){
        String pluginFileString = pluginHolder.getPluginFileString();
        Plugin plugin = pluginHolder.getPluginWrapper().getPlugin();
        try {
            boolean enable = plugin.onEnable();
            if(!enable){
                pluginHolder.setLoadCodeAndStatus(PluginLoadCode.PLUGIN_ON_ENABLE_RETURNED_FALSE,
                        "Plugin on enable returned false; plugin cannot be loaded as per plugin");
                L.warn("Plugin Manager: A plugin needs further configuration then reloaded from location {}.",
                        pluginFileString, pluginHolder.getPluginLoadCode());
                return pluginHolder;
            }else {
                pluginHolder.setLoadCodeAndStatus(PluginLoadCode.SUCCESS_ON_ENABLE, "Plugin enabled");
                L.debug("Plugin Manager: Loading plugin success from location {}. Error: {}", pluginFileString,
                        pluginHolder.getPluginLoadCode());
                plugins.put(pluginHolder.getPluginWrapper().getPluginDetails().getPluginManifest().name(), pluginHolder);
                return pluginHolder;
            }
        } catch (Exception e) {
            pluginHolder.setLoadCodeAndStatus(PluginLoadCode.PLUGIN_ON_ENABLE_EXCEPTION, "Exception from Plugin" +
                    ".onEnable()");
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pluginHolder.getPluginLoadCode());
        }
        return pluginHolder;
    }

    // TODO Stop plugin

    // Unload Plugins

    @Override
    public PluginHolder getPlugin(String name) {
        return plugins.get(name);
    }

    @Override
    public Collection<PluginHolder> getPlugins() {
        return plugins.values();
    }


    @Override
    public void addToBlacklist(String filename) {
        // TODO
    }

    @Override
    public void removeFromBlacklist(String filename) {
        // TODO
    }

    @Override
    public <T> T getExtraResource(String name) {
        return (T) extraResources.get(name);
    }

    @Override
    public void addExtraResource(String name, Object resources) {
        extraResources.put(name, resources);
    }

    @Override
    public boolean isRegistered(String name, Object object) {
        // TODO
        return false;
    }

    @Override
    public boolean registered(String name, Object object) {
        // TODO
        return false;
    }
}
