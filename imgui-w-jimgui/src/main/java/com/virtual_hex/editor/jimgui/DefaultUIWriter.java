package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.*;
import com.virtual_hex.editor.data.*;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.JImGui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * Default writer
 *
 * This is used to handleActivation the UI out to the screen, json or binary
 */
public class DefaultUIWriter implements UIWriter<JImGui> {

    public static final UIComponentWriter EMPTY_WRITER = new EmptyComponentReader();

    public static final String EDITOR_ALL_WINDOWS = "emm-0";
    public static final String W_PROJECTS = "w-projects";
    public static final String W_UI_PLUGINS = "w-ui-plugins";
    public static final String W_IMGUI_ABOUT = "w-imgui-about";
    public static final String W_IMGUI_DEMO = "w-imgui-demo";
    public static final String W_IMGUI_METRICS = "w-imgui-metrics";
    public static final String OPEN = "open";

    public int version;
    private UIComponents root;
    public Map<UUID, UIComponentWriter<JImGui, DefaultUIWriter>> uuidSpecificTypeHandlers;
    public Map<Class<?>, UIComponentWriter<JImGui, DefaultUIWriter>> classComponentHandlers;
    public Map<UUID, List<ActivationHandler<JImGui>>> activationHandlers;
    public Map<UUID, List<StateChangeHandler<JImGui>>> stateChangeHandlers;
    public Map<String, WeakHashMap<UIComponent, String>> toggleGroup;
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

        // Here we need a default menu so we can load the correct project format/project

        // TODO Move to a plugin, it should be the?? data i dunno but not a writer.
        // TODO needs to be updated as well, this is from the editor but didnt belong there so much in the final app
        Object editorLoader = null;
        if (editorLoader != null) {
            root = null; // TODO
        } else {
            // TODO Window Settings
            root = new UIComponents();
            // Anything we draw here will go to the debug window if its a widget type that cannot interact
            // directly with JImGui, like a new Text();  would open a debug window and draw text on it
            // Using a menu, window, some settings and options will draw it correctly in its own area

            Collections.addAll(root.uiComponents,
                    // New User Window

                    // The editor menu will turn into a slightly dif component, well have helper methods to extend
                    // add to editor
                    new EditorMainMenu(this),


                    // UI Plugin Window, UI needs to be remember in case the user completely replaces it

                    cToggleGroup(OPEN, W_PROJECTS, new String[]{EDITOR_ALL_WINDOWS}, new ProjectsWindow(

                    )),

                    cToggleGroup(OPEN, W_UI_PLUGINS, new String[]{EDITOR_ALL_WINDOWS}, new ProjectsWindow(
                            // Here we need to show what is loaded by default , We need to create a child first classloader
                            // where these will be loaded, this way same class can be overridden due to isolation
                            new MainMenuBar("")




                    )),


                    cToggleGroup(OPEN, W_IMGUI_ABOUT, new String[]{EDITOR_ALL_WINDOWS}, new ShowAboutWindow()),
                    cToggleGroup(OPEN, W_IMGUI_DEMO, new String[]{EDITOR_ALL_WINDOWS}, new ShowDemoWindow()),
                    cToggleGroup(OPEN, W_IMGUI_METRICS, new String[]{EDITOR_ALL_WINDOWS}, new ShowMetricsWindow())
            );
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

    /**
     *
     * @param component UiComponent we want to listen to for changes in, note that some think will need to call
     *                  writer.handleActivation(Out, UiComponent, Writer) for this action to be called
     * @param activationHandler logic to run on activation
     * @param <C>
     * @return
     */
    public <C extends UIComponent> C createAction(C component, ActivationHandler<JImGui> activationHandler) {
        activationHandlers.computeIfAbsent(component.getId(), uuid -> new ArrayList<>()).add(activationHandler);
        return component;
    }

    /**
     *
     * @param fieldName name of the field to used in reflection to get and set values
     * @param primaryGroup name of the primary group, which when this C UiComponent toggled will set all toggles in this group
     *                     other widgets may be apart of this group already or in the future
     * @param otherGroups name of additional groups to add this C UIComponent too, this can allow stuff to be controlled in groups, these groups
     *                                are not called when the C UiComponent is activated, only the linkStateChangeGroup
     *                                  other widgets may be apart of this group already or in the future
     * @param uiComponent the component we want to add to the toggle groups
     * @param <C>
     * @returnthe component we added to the groups
     */
    public <C extends UIComponent> C cToggleGroup(String fieldName, String primaryGroup, String[] otherGroups, C uiComponent){
        // We want to be apart of a group that mirrors a state
        toggleGroup.computeIfAbsent(primaryGroup, s-> new WeakHashMap<>()).put(uiComponent, fieldName);
        for (String additionalGroup : otherGroups) {
            toggleGroup.computeIfAbsent(additionalGroup, s-> new WeakHashMap<>()).put(uiComponent, fieldName);
        }

        // We want to know if a state changes
        stateChangeHandlers.computeIfAbsent(uiComponent.getId(), uuid -> new ArrayList<>())
                .add(new StateChangeHandler<JImGui>() {
                    @Override
                    public void handle(JImGui out, UIComponent objectChanged, UIWriter parentDrawer) {
                        Map<UIComponent, String> groupMap = toggleGroup.get(primaryGroup);
                        String fieldName = groupMap.get(objectChanged);
                        try {
                            Field field = uiComponent.getClass().getField(fieldName);
                            boolean aBoolean = field.getBoolean(objectChanged);
                            toggleGroup(primaryGroup, aBoolean);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return uiComponent;
    }

    /**
     *
     * @param fieldName name of the field to used in reflection to get and set values
     * @param group name to add the UiComponents to
     * @param uiComponents UiComponents will be all added to the same group, only one in this group can be selected
     *                     at a time, if an item is selected it will trigger and activation handler to set
     *                     all the others in the group to false, and the selected to true
     * @param <C>
     * @return UiComponent used to set up the single toggle
     */
    public <C extends UIComponent> C[] cSingleToggleGroup(String fieldName, String group, C... uiComponents){
        for (int i = 0; i < uiComponents.length; i++) {
            C uiComponent = uiComponents[i];
            // We want to be apart of a group that mirrors a state
            toggleGroup.computeIfAbsent(group, s-> new WeakHashMap<>()).put(uiComponent, fieldName);

            // We want to know if a state changes
            stateChangeHandlers.computeIfAbsent(uiComponent.getId(), uuid -> new ArrayList<>())
                    .add(new StateChangeHandler<JImGui>() {
                        @Override
                        public void handle(JImGui out, UIComponent objectChanged, UIWriter parentDrawer) {
                            Map<UIComponent, String> groupMap = toggleGroup.get(group);
                            String fieldName = groupMap.get(objectChanged);
                            try {
                                Field field = objectChanged.getClass().getField(fieldName);
                                boolean aBoolean = field.getBoolean(objectChanged);
                                toggleGroup(group, uiComponent, !aBoolean);
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
        return uiComponents;
    }

    // should also deselect option for wrapping closed items in reverse calls

    /**
     *
     * @param fieldName name of the field to used in reflection to get and set values
     * @param primaryGroup name of the primary group, which when this C UiComponent toggled will set all toggles
     *                     in this group
     * @param uiComponent the component we want to add to the toggle groups
     * @param <C>
     * @returnthe component we added to the groups
     */
    public <C extends UIComponent> C cToggleGroup(String fieldName, String primaryGroup, C uiComponent){

        // We want to be apart of a group that mirrors a state
        toggleGroup.computeIfAbsent(primaryGroup, s-> new WeakHashMap<>()).put(uiComponent, fieldName);

        // We want to know if a state changes
        stateChangeHandlers.computeIfAbsent(uiComponent.getId(), uuid -> new ArrayList<>())
                .add(new StateChangeHandler<JImGui>() {
                    @Override
                    public void handle(JImGui out, UIComponent objectChanged, UIWriter parentDrawer) {
                        Map<UIComponent, String> groupMap = toggleGroup.get(primaryGroup);
                        String fieldName = groupMap.get(objectChanged);
                        try {
                            Field field = uiComponent.getClass().getField(fieldName);
                            boolean aBoolean = field.getBoolean(objectChanged);
                            toggleGroup(primaryGroup, !aBoolean);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });
        return uiComponent;
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
        Map<UIComponent, String> componentStringMap = toggleGroup.get(group);
        if(componentStringMap != null){
            for (Map.Entry<UIComponent, String> entry : componentStringMap.entrySet()) {
                UIComponent uiComponent = entry.getKey();
                if(!uiComponent.equals(exclude)) {
                    String fieldName = entry.getValue();
                    Field byName = getFieldByName(uiComponent, fieldName);
                    try {
                        byName.setBoolean(uiComponent, value);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
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
        List<StateChangeHandler<JImGui>> stateChangeHandlerList = stateChangeHandlers.getOrDefault(uiComponent.getId(), Collections.emptyList());
        for (StateChangeHandler<JImGui> stateChangeHandler : stateChangeHandlerList) {
            stateChangeHandler.handle(out, uiComponent, writer);
        }
    }

    protected Field getFieldByName(UIComponent component, String name){
        Class<? extends UIComponent> componentClass = component.getClass();
        try {
            return componentClass.getField(name);
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
        this.uuidSpecificTypeHandlers = new HashMap<>();
        this.classComponentHandlers = new HashMap<>();
        this.activationHandlers = new HashMap<>();
        this.toggleGroup = new HashMap<>();
        this.stateChangeHandlers = new HashMap<>();
        this.properties = new ConcurrentHashMap<>(16, 1.0f, 2);
    }

    @Override
    public void write(JImGui out) {
        UIComponentWriter<JImGui, DefaultUIWriter> componentWriter = null;

        componentWriter = uuidSpecificTypeHandlers.get((root).getId());

        if (componentWriter == null) {
            componentWriter = checkType(root);
        }

        if(componentWriter != null){
            componentWriter.write(out, root, this);
        }
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

        componentWriter = uuidSpecificTypeHandlers.get((uiComponent).getId());

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

        componentWriter = uuidSpecificTypeHandlers.get((uiComponent).getId());

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
        Class<?> clazzType = null;
        // TODO This could be cached if need to be faster
        int depthTries = 3;
        int tries = 0;
        while(componentReader == null && tries < depthTries){
            clazzType = aClass.getSuperclass();
            componentReader = classComponentHandlers.get(clazzType);
            tries++;
            if(clazzType == Object.class) {
                System.err.println("Could not find a class read, tried " + aClass + " got " + componentReader);
                break;
            }
        }
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
        List<ActivationHandler<JImGui>> activationHandlerList = activationHandlers.getOrDefault(uiComponent.getId(), Collections.emptyList());
        for (ActivationHandler<JImGui> activationHandler : activationHandlerList) {
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
