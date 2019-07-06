package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.*;
import com.virtual_hex.editor.data.Selectable;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImVec4;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Default writer
 *
 * This is used to handleActivation the UI out to the screen, json or binary
 */
public class DefaultUIWriter implements UIWriter<JImGui> {

    /**
     * Simply a Logger Reference
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUIWriter.class);


    public static final UIComponentWriter EMPTY_WRITER = new EmptyComponentReader();

    public int version;
    public DeallocatableObjectManager deallocatableObjectManager = new DeallocatableObjectManager();
    public UIComponent[] root = {};
    public Map<UIComponent, UIComponentWriter<JImGui, DefaultUIWriter>> uuidSpecificTypeHandlers;
    public Map<Class<?>, UIComponentWriter<JImGui, DefaultUIWriter>> classComponentHandlers;
    public Map<UIComponent, List<ActivationHandler>> activationHandlers;
    public Map<UIComponent, List<StateChangeHandler>> stateChangeHandlers;
    public Map<UIComponent, List<AlwaysHandler>> alwaysHandlers;



    /**
     * Implementation here is Weakmap and Weakset, for auto garbage collection
     */
    public Map<String, Map<UIComponent, Field>> toggleGroups;
    // Reverse look up for making sure if set from one group, we trigger all groups
    public Map<UIComponent, List<String>> groupMembership;


    public ConcurrentHashMap<String, Object> properties;
    private Path pluginDirectory;

    // TODO update since api change
    // Scans could be provided to provide more app level configuration
    public DefaultUIWriter() {
        setMaps();

        // Needs to load by a setting or use default settings much like the UIWriter

        ScanResult editorPackageScan = new ClassGraph().enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();
        ClassInfoList uiComponentWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIComponentWriter").filter(classInfo -> classInfo.hasAnnotation("com.virtual_hex.editor.ComponentRegister"));
        for (ClassInfo uiComponentWriter : uiComponentWriters) {

            Class<UIComponentWriter> aClass = (Class<UIComponentWriter>) uiComponentWriter.loadClass();
            try {
                UIComponentWriter componentWriter = aClass.newInstance();
                ComponentRegister annotation = aClass.getDeclaredAnnotation(ComponentRegister.class);
                classComponentHandlers.put(annotation.typeKey(), componentWriter);
            } catch (Exception e) {
                e.printStackTrace();
                // Todo Logging for failed class loading
            }
        }
    }

//    public DefaultUIWriter(UIComponents root, int version, boolean scanForHandlers, ScanResult... scanResults) {
//        this.root = root;
//        this.version = version;
//        setMaps();
//        if (scanForHandlers) {
//            scanForHandlers(scanResults);
//        }
//    }


//
//    public <C extends UIComponent> C[] addToRoot(C... uiComponents){
//        // TODO
//    }
//
//    public <C extends UIComponent> C addToRoot(C uiComponents){
//        // TODO
//    }


    public <C extends UIComponent> C createAlwaysHandle(C component, ActivationHandler<JImGui, ?> activationHandler) {
        activationHandlers.computeIfAbsent(component, uuid -> new ArrayList<>()).add(activationHandler);
        return component;
    }

    /**
     *
     * @param component UiComponent we want to listen to for changes in, note that some think will need to call
     *                  writer.handleActivation(Out, UiComponent, Writer) for this action to be called
     * @param activationHandler logic to run on activation
     * @param <C>
     * @return
     */
    public <C extends UIComponent> C createAction(C component, ActivationHandler<JImGui, ?> activationHandler) {
        activationHandlers.computeIfAbsent(component, uuid -> new ArrayList<>()).add(activationHandler);
        return component;
    }

    // boolean if we should include a state handler for this
    public UIComponent[] addToggleGroup(String group, UIComponent[] uiComponents){
        for (int i = 0; i < uiComponents.length; i++) {
            addToggle(group, uiComponents[i]);
        }
        return uiComponents;
    }

    public UIComponent addToggleGroup(String group, UIComponent uiComponent){
        addToggle(group, uiComponent);
        return uiComponent;
    }



    /**
     * Each component when state changes will match the state of the other toggles.
     *
     * @param mainComponent
     * @return
     */
    public UIComponent bindTogglesAddRoot(String group, UIComponent mainComponent, UIComponent toRoot){
        toggleGroups.computeIfAbsent(group, new Function<String, Map<UIComponent, Field>>() {
            @Override
            public Map<UIComponent, Field> apply(String s) {
                // We want all the references to be garbage collected in case the UI Widgets are unloaded
                WeakHashMap<UIComponent, Field> toggles = new WeakHashMap<>();
                addToggle(group, mainComponent);
                addToggle(group, toRoot);
                addToggleStateChaneHandlers(group, mainComponent, toRoot);
                root = UIUtils.merge(root, new UIComponent[]{toRoot});
                return toggles;
            }
        });

        return mainComponent;
    }

    /**
     * Each component when state changes will match the state of the other toggles.
     *
     * @param uiComponents
     * @return
     */
    public UIComponent[] bindToggles(String group, UIComponent... uiComponents){
        toggleGroups.computeIfAbsent(group, new Function<String, Map<UIComponent, Field>>() {
            @Override
            public Map<UIComponent, Field> apply(String s) {
                // We want all the references to be garbage collected in case the UI Widgets are unloaded
                WeakHashMap<UIComponent, Field> toggles = new WeakHashMap<>();
                addToggles(group, uiComponents);
                addToggleStateChaneHandlers(group, uiComponents);
                return toggles;
            }
        });

        return uiComponents;
    }

    private void addToggleStateChaneHandlers(String group, UIComponent... uiComponents) {
        for (int i = 0; i < uiComponents.length; i++) {
            addToggleStateChaneHandlers(group, uiComponents[i]);
        }
    }

    private void addToggleStateChaneHandlers(String group, UIComponent uiComponent) {
        stateChangeHandlers
                .computeIfAbsent(uiComponent, uuid -> new ArrayList<>())
                .add(new StateChangeHandler<JImGui, DefaultUIWriter>() {
            @Override
            public void handle(JImGui out, UIComponent objectChanged, DefaultUIWriter parentDrawer) {
                Map<UIComponent, Field> toggles = toggleGroups.get(group);
                Field field = toggles.get(objectChanged);
                if(field == null){
                    LOGGER.error("Error could not find a toggle for ui component: {} with id of {} and a group of {}", uiComponent, uiComponent.getId(), group);
                    return;
                }
                try {
                    boolean value = field.getBoolean(objectChanged);
                    // Exclude object changed from the toggle
                    toggleGroup(group, objectChanged, value);
                } catch (IllegalAccessException e) {
                    LOGGER.error("Error could not get a boolean from field for ui component: {} with id of {}   and a group of {}.Stack message {}", uiComponent, uiComponent.getId(), group, e.getMessage());
                }
            }
        });
    }

    private void addToggles(String group, UIComponent uiComponent, UIComponent... uiComponents){
        addToggle(group, uiComponent);
        addToggles(group, uiComponents);
    }

    // If it fails it will not do harm and the widget will not be apart of a group
    private void addToggles(String group, UIComponent... uiComponents){
        for (int i = 0; i < uiComponents.length; i++) {
            addToggle(group, uiComponents[i]);
        }
    }

    private void addToggle(String group, UIComponent uiComponent){
        Toggle toggleField = uiComponent.getClass().getAnnotation(Toggle.class);
        String fieldName = toggleField.fieldName();
        try {
            Field field = uiComponent.getClass().getDeclaredField(fieldName);
            if(field != null){
                toggleGroups.computeIfAbsent(group, s -> new WeakHashMap<>()).putIfAbsent(uiComponent, field);
                groupMembership.computeIfAbsent(uiComponent, uuid -> new ArrayList<>()).add(group);
            }
        } catch (NoSuchFieldException e) {
            LOGGER.error("Could not find field name \"{}\" in class \"{}\" and a group of {}", fieldName, uiComponent.getClass(), group, e);
        }
    }

    /**
     *
     * @param group the group to toggle
     * @param value the value to set the for each value field of the group
     */
    public void toggleGroup(String group, boolean value){
        toggleGroup(group, UIComponent.EMPTY_COMPONENT, value);
    }

    /**
     *
     * @param group the group to toggle
     * @param exclude UiComponent to exclude from the toggle
     * @param value the value to set the for each value field of the group
     */
    public void toggleGroup(String group, UIComponent exclude, boolean value){
        Map<UIComponent, Field> fieldMap = toggleGroups.get(group);
        if(fieldMap != null){
            for (Map.Entry<UIComponent, Field> entry : fieldMap.entrySet()) {
                UIComponent uiComponent = entry.getKey();
                if(!uiComponent.equals(exclude)) {
                    Field field = entry.getValue();
                    try {
                        field.set(uiComponent, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        LOGGER.error("Could not set field type boolean \"{}\" in class \"{}\" and a group of {}", field, uiComponent.getClass(), group, e);
                    }
                }
            }
            List<String> strings = groupMembership.get(exclude);
            if(strings != null){
                for (String string : strings) {
                    toggleGroup(string, value);
                }
            }
        }
    }

    /**
     * This will notify all the state change listeners of a UiComponent of a state change
     *
     * @param out
     * @param uiComponent
     * @param writer
     */
    public void handleStateChange(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        List<StateChangeHandler> stateChangeHandlerList = stateChangeHandlers.getOrDefault(uiComponent.getId(), Collections.emptyList());
        for (StateChangeHandler stateChangeHandler : stateChangeHandlerList) {
            stateChangeHandler.handle(out, uiComponent, writer);
        }
    }

    protected Field getFieldByName(UIComponent component, String name){
        Class<? extends UIComponent> componentClass = component.getClass();
        try {
            return componentClass.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected Field getFieldByAnnotationName(UIComponent component, Class<? extends Annotation> annotationClass, String name){
        Class<? extends UIComponent> toggleMechanism = component.getClass();
        Field[] fields = toggleMechanism.getDeclaredFields();
        Field fieldFrom = null;
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            Annotation annotationPresent = field.getAnnotation(annotationClass);
            if(annotationPresent != null){
                if(field.getName().equals(name)){
                    fieldFrom = field;
                    break;
                }
            }
        }
        return fieldFrom;
    }

    // TODO Update with better method
    private void scanForHandlers(ScanResult... scanResults) {
        for (int i = 0; i < scanResults.length; i++) {
            ScanResult scanResult = scanResults[i];

            ClassInfoList componentHandlerRegister = scanResult.getClassesWithAnnotation("com.virtual_hex.editor.io.ComponentRegister");
            for (ClassInfo compClassInfo : componentHandlerRegister) {
                boolean extendsSuperclass = compClassInfo.implementsInterface("com.virtual_hex.editor.io.UIComponentWriter");
                if (extendsSuperclass) {
                    // New handler, should be registered
                    Class<UIComponentWriter> aClass = (Class<UIComponentWriter>) compClassInfo.loadClass();
                    try {
                        UIComponentWriter componentWriter = aClass.newInstance();

                    } catch (Exception e) {
                        e.printStackTrace();
                        // Todo Logging for failed class loading
                    }
                } else {
                    // TODO Logging that annotation exist but not a subclass of CompHandler
                }
            }
        }
    }

    /**
     * Set up the initial maps
     */
    private void setMaps() {
        this.uuidSpecificTypeHandlers = new WeakHashMap<>();
        this.classComponentHandlers = new HashMap<>();
        this.activationHandlers = new WeakHashMap<>();
        this.toggleGroups = new HashMap<>();
        this.stateChangeHandlers = new WeakHashMap<>();
        this.alwaysHandlers = new WeakHashMap<>();
        this.groupMembership = new WeakHashMap<>();
        this.properties = new ConcurrentHashMap<>(16, 1.0f, 2);
    }

    @Override
    public void write(JImGui out) {
        JImGuiComponentWriter.processArray(out, root, this);
    }



    /**
     * Returns false if a failure to write, allowing the developer to report it to the user
     *
     * @param out
     * @param uiComponent
     * @return
     */
    public void write(JImGui out, UIComponent uiComponent, BiConsumer<JImGui, UIComponent> writeFailHandler) {
        UIComponentWriter<JImGui, DefaultUIWriter> componentWriter = null;

        componentWriter = uuidSpecificTypeHandlers.get(uiComponent);

        if (componentWriter == null) {
            componentWriter = checkType(uiComponent);
        }

        if(componentWriter == null){
            writeFailHandler.accept(out, uiComponent);
        } else {
            componentWriter.write(out, uiComponent, this);
        }
    }

    /**
     * Returns false if a failure to write, allowing the developer to report it to the user
     *
     * @param out
     * @param uiComponent
     * @return
     */
    public void write(JImGui out, UIComponent uiComponent) {
        UIComponentWriter<JImGui, DefaultUIWriter> componentWriter = null;

        componentWriter = uuidSpecificTypeHandlers.get(uiComponent);

        if (componentWriter == null) {
            componentWriter = checkType(uiComponent);
        }

        if(componentWriter != null){
            componentWriter.write(out, uiComponent, this);
        }
    }

    /**
     *
     * @param uiComponent
     * @return
     */
    private UIComponentWriter<JImGui, DefaultUIWriter> checkType(UIComponent uiComponent) {
        Class<?> aClass = uiComponent.getClass();
        UIComponentWriter<JImGui, DefaultUIWriter> componentReader = classComponentHandlers.get(aClass);
//        Class<?> clazzType = null;
//        // TODO This could be cached if need to be faster
//        int depthTries = 3;
//        int tries = 0;
//        while(componentReader == null && tries < depthTries){
//            clazzType = aClass.getSuperclass();
//            componentReader = classComponentHandlers.get(clazzType);
//            tries++;
//            if(clazzType == Object.class) {
//                System.err.println("Could not find a class read, tried " + aClass + " got " + componentReader);
//                break;
//            }
//        }
        return componentReader;
    }


    /**
     * This is called by a widget when it has been activated, all activation listeners will be notified
     *
     * @param out
     * @param uiComponent
     * @param writer
     */
    public void handleActivation(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        List<ActivationHandler> activationHandlerList = activationHandlers.getOrDefault(uiComponent, Collections.emptyList());
        for (ActivationHandler activationHandler : activationHandlerList) {
            activationHandler.handle(out, uiComponent, writer);
        }
    }

    /**
     * Dispose of resources here, WE may have a cache clear method for memory management if needed
     */
    public void disopse() {
        uuidSpecificTypeHandlers.forEach((k, v) -> v.dispose());
        classComponentHandlers.forEach((k, v) -> v.dispose());
//        activationHandlers.forEach((k, v) -> v.dispose());
    }

    @Override
    public void setPluginDataPath(Path path) {
        this.pluginDirectory = path;
    }

    @Override
    public Path getPluginDataPath(String folder) {
        return pluginDirectory.resolve(folder);
    }

    @Override
    public Object setProperty(String key, Object property) {
        return properties.put(key, property);
    }

    @Override
    public <T> T getProperty(String key) {
        return (T) properties.get(key);
    }

    @Override
    public void dispose() {
        uuidSpecificTypeHandlers.forEach((uuid, uic) -> uic.dispose());
        classComponentHandlers.forEach((uuid, uic) -> uic.dispose());
        deallocatableObjectManager.deallocateAll();
    }

    public Object getJIVec4() {
        return getJIVec4(0, 0, 0, 0);
    }

    public Object getJIVec4(int x, int y, int z) {
        return getJIVec4(x, y, z, 0);
    }

    public Object getJIVec4(int x, int y, int z, int w) {
        JImVec4 jImVec4 = new JImVec4(x, y, z, w);
        deallocatableObjectManager.add(jImVec4);
        return jImVec4;
    }


    public <LABEL> Selectable<LABEL> setStateChangeListener(Selectable<LABEL> selectable, StateChangeHandler stateChangeHandler) {
        stateChangeHandlers.computeIfAbsent(selectable, uuid -> new ArrayList<>()).add(stateChangeHandler);
        return selectable;
    }

    private static class EmptyComponentReader implements UIComponentWriter {

        @Override
        public void write(Object out, UIComponent uiComponent, Object writer) {
            // Nothing Intended
        }

        @Override
        public void dispose() {
            // Nothing Intended
        }
    }
}
