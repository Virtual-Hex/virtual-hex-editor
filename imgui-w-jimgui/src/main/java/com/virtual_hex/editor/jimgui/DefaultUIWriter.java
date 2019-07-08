package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.*;
import com.virtual_hex.editor.data.Image;
import com.virtual_hex.editor.data.ImageButton;
import com.virtual_hex.editor.data.Selectable;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.utils.UIUtils;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import org.ice1000.jimgui.*;
import org.ice1000.jimgui.cpp.DeallocatableObjectManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
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


    /**
     * These get cached first cycle through
     */
    protected Map<UIComponent, Map<String, NativeInt>> cachedInts = new WeakHashMap<>();
    /**
     * These get cached first cycle through
     */
    protected Map<UIComponent, Map<String, NativeFloat>> cachedFloats = new WeakHashMap<>();

    /**
     * These get cached first cycle through
     */
    protected Map<UIComponent, Map<String, NativeDouble>> cachedDoubles = new WeakHashMap<>();

    /**
     * These get cached first cycle through
     */
    protected Map<UIComponent, Map<String, NativeBool>> cachedBools = new WeakHashMap<>();


    public Map<UIComponent, JImTextureID> cachedImageReferences = new WeakHashMap<>();

    public ConcurrentHashMap<String, Object> properties;
    private Path pluginDirectory;

    // TODO update since api change
    // Scans could be provided to provide more app level configuration
    public DefaultUIWriter() {
        setMaps();

        // Needs to load by a setting or use default settings much like the UIWriter

        ScanResult editorPackageScan = new ClassGraph().enableAllInfo().whitelistPackages("com.virtual_hex.editor").scan();

        // Get Component Registers
        ClassInfoList uiComponentWriters = editorPackageScan.getClassesImplementing("com.virtual_hex.editor.UIComponentWriter").filter(classInfo -> classInfo.hasAnnotation("com.virtual_hex.editor.UIComponentRegister"));
        for (ClassInfo uiComponentWriter : uiComponentWriters) {

            Class<UIComponentWriter> aClass = (Class<UIComponentWriter>) uiComponentWriter.loadClass();
            try {
                UIComponentWriter componentWriter = aClass.newInstance();
                UIComponentRegister annotation = aClass.getDeclaredAnnotation(UIComponentRegister.class);
                classComponentHandlers.put(annotation.typeKey(), componentWriter);
            } catch (Exception e) {
                e.printStackTrace();
                // Todo Logging for failed class loading
            }
        }

        // Scan the clas for methods and fields
        ClassInfoList uiComponentsRegister = editorPackageScan.getClassesWithAnnotation("com.virtual_hex.editor.UIComponentsRegister");
        for (ClassInfo classInfo : uiComponentsRegister) {
            Class<?> aClass = classInfo.loadClass();
            try {
                Object newInstance = aClass.newInstance();

                Field[] declaredFields = aClass.getDeclaredFields();
                for (int i = 0; i < declaredFields.length; i++) {
                    Field declaredField = declaredFields[i];
                    UIComponentRegister annotation = declaredField.getAnnotation(UIComponentRegister.class);
                    if(annotation == null){
                        return;
                    }

                    Class<?> typeKey = annotation.typeKey();
                    classComponentHandlers.put(typeKey, (UIComponentWriter<JImGui, DefaultUIWriter>) declaredField.get(newInstance));
                }

                Method[] declaredMethods = aClass.getDeclaredMethods();
                for (int i = 0; i < declaredMethods.length; i++) {
                    Method declaredField = declaredMethods[i];
                    UIComponentRegister annotation = declaredField.getAnnotation(UIComponentRegister.class);
                    if(annotation == null){
                        return;
                    }

                    Class<?> typeKey = annotation.typeKey();
                    try {
                        classComponentHandlers.put(typeKey, (UIComponentWriter<JImGui, DefaultUIWriter>) declaredField.invoke(newInstance));
                    } catch (InvocationTargetException e) {
                        // TODO Proper logging
                        e.printStackTrace();
                    }
                }

            } catch (InstantiationException | IllegalAccessException e) {
                // TODO Proper logging
                e.printStackTrace();
            }
        }

        // Get Components Registers
        // Fields and methods
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

    public UIComponent addToRoot(UIComponent uiComponent){
        root = UIUtils.merge(root, new UIComponent[]{uiComponent});
        return uiComponent;
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

            ClassInfoList componentHandlerRegister = scanResult.getClassesWithAnnotation("com.virtual_hex.editor.io.UIComponentRegister");
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
        UIComponentWriter.processArray(out, root, this);
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
        deallocatableObjectManager.deallocateAll();
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


    public void clearCache(){
        // TODO Stub
    }

    public NativeInt getCachedNativeInt(String fieldName, UIComponent object) {
        return cachedInts
                .computeIfAbsent(object, value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> createNativeInt());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeInt createNativeInt() {
        NativeInt nativeValue = new NativeInt();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    public NativeFloat getCachedNativeFloat(String fieldName, UIComponent object) {
        return cachedFloats
                .computeIfAbsent(object, value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> createNativeFloat());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeFloat createNativeFloat() {
        NativeFloat nativeValue = new NativeFloat();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    public NativeDouble getCachedNativeDouble(String fieldName, UIComponent object) {
        return cachedDoubles
                .computeIfAbsent(object, value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> createNativeDouble());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeDouble createNativeDouble() {
        NativeDouble nativeValue = new NativeDouble();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }


    public NativeBool getCachedNativeBool(String fieldName, UIComponent object) {
        return cachedBools
                .computeIfAbsent(object, value -> new HashMap<>())
                .computeIfAbsent(fieldName, aClass -> createNativeBool());
    }

    /**
     * Automatically will deallocate this when the JawImGui is disposed of
     *
     * @return
     */
    public NativeBool createNativeBool() {
        NativeBool nativeValue = new NativeBool();
        deallocatableObjectManager.add(nativeValue);
        return nativeValue;
    }

    public JImTextureID getTextureId(ImageButton<?, ?> image, DefaultUIWriter writer){
        // TODO Could use a group type handler by name or id and place that into writer, by using
        // the writer to createNativeInt a cache cleaning mechanism here

        JImTextureID imTextureID = cachedImageReferences.get(image);
        if(imTextureID != null){
            return imTextureID;
        } else {
            Object object = image.from;
            if(object instanceof String){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((String) object));
            } else if(object instanceof File){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((File) object));
            } else if(object instanceof Path){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromPath((Path) object));
            } else if(object instanceof byte[]){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromBytes((byte[]) object));
            } else if (object instanceof Long) {
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromExistingID((long) object, image.width, image.height));
            } else if (object instanceof URI){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromUri((URI) object));
            } else {
                return null;
            }
        }
    }


    public JImTextureID getTextureId(Image<?, ?> image, DefaultUIWriter writer){
        // TODO Could use a group type handler by name or id and place that into writer, by using
        // the writer to createNativeInt a cache cleaning mechanism here

        JImTextureID imTextureID = cachedImageReferences.get(image);
        if(imTextureID != null){
            return imTextureID;
        } else {
            Object object = image.from;
            if(object instanceof String){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((String) object));
            } else if(object instanceof File){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromFile((File) object));
            } else if(object instanceof Path){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromPath((Path) object));
            } else if(object instanceof byte[]){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromBytes((byte[]) object));
            } else if (object instanceof Long) {
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromExistingID((long) object, image.width, image.height));
            } else if (object instanceof URI){
                return cachedImageReferences.putIfAbsent(image,  JImTextureID.fromUri((URI) object));
            } else {
                return null;
            }
        }
    }

    @Override
    public void dispose() {
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

        private void dispose() {
            // Nothing Intended
        }
    }
}
