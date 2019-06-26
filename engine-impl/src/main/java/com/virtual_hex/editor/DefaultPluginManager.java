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

    private final Path pluginDataPath;
    private final String target;
    private final Map<String, PluginHolder> plugins;

    public DefaultPluginManager(Path pluginDataPath, String target) {
        this.pluginDataPath = pluginDataPath;
        this.target = target;
        this.plugins = new ConcurrentHashMap<>(16, 0.75f,1);
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
    public List<PluginHolder> loadPlugins(Path... files) {
        int length = files.length;
        List<PluginHolder> plugins = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            Path file = files[i];
            PluginHolder pluginHolder = loadPlugin(file.toFile());
            plugins.add(pluginHolder);
        }
        return plugins;
    }

    @Override
    public PluginHolder loadPlugin(Path file){
        return loadPlugin(file.toFile());
    }

    @Override
    public PluginHolder loadPlugin(File file){
        // TODO Plugin Manager Name and Application for Debugging
        PluginHolder.StatusCode pl;

        final String pluginFileString = file.toString();

        L.trace("Plugin Manager: Loading plugin from location {}", pluginFileString);

        JarFile pluginJarFile;
        try {
            pluginJarFile = new JarFile(file);
        } catch (IOException e) {
            pl = PluginHolder.StatusCode.JAR_FILE_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        Manifest pluginJarManifest;
        try {
            pluginJarManifest = pluginJarFile.getManifest();
        } catch (IOException e) {
            pl = PluginHolder.StatusCode.JAR_MANIFEST_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        if (pluginJarManifest == null) {
            pl = PluginHolder.StatusCode.NO_JAR_MANIFEST;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "META-INF/MANIFEST.MF");
        }

        final String pluginClassString = pluginJarManifest.getMainAttributes().getValue("Plugin-Class");
        if (pluginClassString == null) {
            pl = PluginHolder.StatusCode.NO_JAR_MANIFEST_VALUE;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Plugin-Class");
        }

        String entryNameTemp = pluginClassString.replace(".", "/");
        String entryName = entryNameTemp.endsWith(".class") ? entryNameTemp : entryNameTemp + ".class";
        JarEntry jarEntry = pluginJarFile.getJarEntry(entryName);
        if (jarEntry == null) {
            pl = PluginHolder.StatusCode.CANNOT_FIND_CLASS_JAR_ENTRY;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, entryName);
        }

        PluginClazzLoader pluginClazzLoader = null;
        try {
            pluginClazzLoader = new PluginClazzLoader(new URL[]{file.toURI().toURL()});
        } catch (MalformedURLException e) {
            pl = PluginHolder.StatusCode.MALFORMED_URL;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        Class<?> pluginClazz = null;
        try {
            pluginClazz = pluginClazzLoader.loadClass(pluginClassString);
        } catch (ClassNotFoundException e) {
            pl = PluginHolder.StatusCode.CANNOT_FIND_CLASS_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        if (!Plugin.class.isAssignableFrom(pluginClazz)) {
            pl = PluginHolder.StatusCode.CLASS_PLUGIN_INHERITANCE;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Must inherit interface \"Plugin\"");
        }

        PluginManifest pluginManifest = pluginClazz.getAnnotation(PluginManifest.class);
        if(pluginManifest == null){
            pl = PluginHolder.StatusCode.MISSING_ANNOTATION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "Plugin class is missing the annotation \"PluginManifest\"");
        }

        String[] targets = pluginManifest.targets();
        if (targets.length == 0) {
            pl = PluginHolder.StatusCode.NO_TARGET;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, "The plugin must designate a target");
        }

        boolean targetMatch = false;
        int targetsLength = targets.length;
        for (int i = 0; i < targetsLength; i++) {
            String target = targets[i];
            if(target != null){
                if(!Objects.equals(this.target, target)){
                    targetMatch = true;
                    break;
                }
            }
        }

        if (!targetMatch) {
            pl = PluginHolder.StatusCode.TARGET_MISS_MATCH;
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
                    pl = PluginHolder.StatusCode.ANNOTATION_METHOD_ILLEGAL_ACCESS_EXCEPTION;
                    L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
                    return new PluginHolder(pl, pluginFileString, e.getMessage());
                } catch (InvocationTargetException e) {
                    pl = PluginHolder.StatusCode.ANNOTATION_METHOD_INVOCATION_TARGET_EXCEPTION;
                    L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
                    return new PluginHolder(pl, pluginFileString, e.getMessage());
                }
                if(nullOrEmpty) {
                    pl = PluginHolder.StatusCode.MISSING_ANNOTATION_VALUE;
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
            pl = PluginHolder.StatusCode.PLUGIN_INSTANTIATION_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        } catch (IllegalAccessException e) {
            pl = PluginHolder.StatusCode.PLUGIN_ILLEGAL_ACCESS_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }

        PluginDetails pluginDetails = new PluginDetails(pluginManifest, version, dependenciesStringArray);
        Path pluginFilePath = Paths.get(pluginFileString);
        long pluginSize;
        try {
            pluginSize = Files.size(pluginFilePath);
        } catch (IOException e) {
            pl = PluginHolder.StatusCode.PLUGIN_FILE_SIZE_IO_EXCEPTION;
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pl);
            return new PluginHolder(pl, pluginFileString, e.getMessage());
        }
        Path runningPluginDirectory = pluginFilePath.getParent();

        Path pluginDataDirectory = pluginDataPath.resolve(pluginManifest.name());
        try {
            Files.createDirectories(pluginDataDirectory);
        } catch (IOException e) {
            pl = PluginHolder.StatusCode.PLUGIN_DATA_DIRECTORY_CREATE_ERROR;
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

        pl = PluginHolder.StatusCode.SUCCESS_LOAD;
        return new PluginHolder(pl, pluginFileString, "Success Load");
    }

    @Override
    public PluginHolder startPlugin(PluginHolder pluginHolder){
        String pluginFileString = pluginHolder.getPluginFileString();
        Plugin plugin = pluginHolder.getPluginWrapper().getPlugin();
        try {
            boolean enable = plugin.onEnable();
            if(!enable){
                pluginHolder.setLoadCodeAndStatus(PluginHolder.StatusCode.PLUGIN_ON_ENABLE_RETURNED_FALSE,
                        "Plugin on enable returned false; plugin cannot be loaded as per plugin");
                L.warn("Plugin Manager: A plugin needs further configuration then reloaded from location {}.",
                        pluginFileString, pluginHolder.getStatusCode());
                return pluginHolder;
            }else {
                pluginHolder.setLoadCodeAndStatus(PluginHolder.StatusCode.SUCCESS_ON_ENABLE, "Plugin enabled");
                L.debug("Plugin Manager: Loading plugin success from location {}. Error: {}", pluginFileString,
                        pluginHolder.getStatusCode());
                plugins.put(pluginHolder.getPluginWrapper().getPluginDetails().getPluginManifest().name(), pluginHolder);
                return pluginHolder;
            }
        } catch (Exception e) {
            pluginHolder.setLoadCodeAndStatus(PluginHolder.StatusCode.PLUGIN_ON_ENABLE_EXCEPTION, "Exception from Plugin" +
                    ".onEnable()");
            L.warn("Plugin Manager: Loading plugin error from location {}. Error: {}", pluginFileString, pluginHolder.getStatusCode());
        }
        return pluginHolder;
    }

    @Override
    public PluginHolder[] startPlugins(PluginHolder... pluginHolders) {
        for (int i = 0; i < pluginHolders.length; i++) {
            PluginHolder pluginHolder = pluginHolders[i];
            startPlugin(pluginHolder);
        }
        return pluginHolders;
    }

    @Override
    public PluginHolder stopPlugin(PluginHolder pluginHolder) {
        pluginHolder.getPluginWrapper().getPlugin().onDisable();
        return pluginHolder;
    }

    @Override
    public PluginHolder[] stopPlugins(PluginHolder... pluginHolders) {
        for (int i = 0; i < pluginHolders.length; i++) {
            PluginHolder pluginHolder = pluginHolders[i];

            pluginHolder.getPluginWrapper().getPlugin().onDisable();
        }
        return pluginHolders;
    }

    @Override
    public PluginHolder unloadPlugin(PluginHolder pluginHolder) {
        stopPlugins(pluginHolder);
        plugins.remove(pluginHolder.getPluginWrapper().getPluginDetails().getPluginManifest().name());
        return pluginHolder;
    }

    @Override
    public PluginHolder[] unloadPlugins(PluginHolder... pluginHolders) {
        for (int i = 0; i < pluginHolders.length; i++) {
            PluginHolder pluginHolder = pluginHolders[i];
            stopPlugins(pluginHolder);
            plugins.remove(pluginHolder.getPluginWrapper().getPluginDetails().getPluginManifest().name());
        }
        return pluginHolders;
    }

    @Override
    public PluginHolder getPlugin(String name) {
        return plugins.get(name);
    }

    @Override
    public Map<String, PluginHolder> getPlugins() {
        return plugins;
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
