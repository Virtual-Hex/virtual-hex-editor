package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * Default writer
 *
 * This is used to handleActivation the UI out to the screen, json or binary
 *
 * @param <T> UI interface
 */
public class UIWriter<T> {

    public static final UIComponentWriter EMPTY_WRITER = new EmptyComponentReader();

    public final int version;
    public Map<UUID, UIComponentWriter<T>> uuidSpecificTypeHandlers;
    public Map<Class<?>, UIComponentWriter<T>> classComponentHandlers;
    public Map<UUID, List<ActivationHandler<T>>> activationHandlers;
    public Map<UUID, List<StateChangeHandler<T>>> stateChangeHandlers;


    public Map<String, WeakHashMap<UIComponent, String>> toggleGroup;

    // Scans could be provided to provide more app level configuration
    public UIWriter(boolean scanForHandlers, ScanResult... scanResults) {
        this(0, scanForHandlers, scanResults);
    }

    public UIWriter(int version, boolean scanForHandlers, ScanResult... scanResults) {
        this.version = version;
        setMaps();
        if (scanForHandlers) {
            scanForHandlers(scanResults);
        }
    }

    /**
     *
     * @param component UiComponent we want to listen to for changes in, note that some think will need to call
     *                  writer.handleActivation(Out, UiComponent, Writer) for this action to be called
     * @param activationHandler logic to run on activation
     * @param <C>
     * @return
     */
    public <C extends UIComponent> C createAction(C component, ActivationHandler<T> activationHandler) {
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
                .add(new StateChangeHandler<T>() {
                    @Override
                    public void handle(T out, UIComponent objectChanged, UIWriter<T> parentDrawer) {
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
                    .add(new StateChangeHandler<T>() {
                        @Override
                        public void handle(T out, UIComponent objectChanged, UIWriter<T> parentDrawer) {
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
                .add(new StateChangeHandler<T>() {
                    @Override
                    public void handle(T out, UIComponent objectChanged, UIWriter<T> parentDrawer) {
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
    public void handleStateChange(T out, UIComponent uiComponent, UIWriter<T> writer) {
        List<StateChangeHandler<T>> stateChangeHandlerList = stateChangeHandlers.getOrDefault(uiComponent.getId(), Collections.emptyList());
        for (StateChangeHandler<T> stateChangeHandler : stateChangeHandlerList) {
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
                        ComponentRegister annotation = componentWriter.getClass().getAnnotation(ComponentRegister.class);
                        if (annotation.operation() == ComponentOperation.WRITE || annotation.operation() == ComponentOperation.READ_WRITE) {
                            classComponentHandlers.put(annotation.typeKey(), componentWriter);
                        }
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
    }


    /**
     * Returns false if a failure to write, allowing the developer to report it to the user
     *
     * @param out
     * @param uiComponent
     * @return
     */
    public void write(T out, UIComponent uiComponent, BiConsumer<T, UIComponent> writeFailHandler) {
        UIComponentWriter<T> componentWriter = null;

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
    public boolean write(T out, UIComponent uiComponent) {
        UIComponentWriter<T> componentWriter = null;

        componentWriter = uuidSpecificTypeHandlers.get((uiComponent).getId());

        if (componentWriter == null) {
            componentWriter = checkType(uiComponent);
        }

        if(componentWriter == null){
            return false;
        } else {
            componentWriter.write(out, uiComponent, this);
            return true;
        }
    }

    /**
     *
     * @param uiComponent
     * @return
     */
    private UIComponentWriter<T> checkType(UIComponent uiComponent) {
        Class<?> aClass = uiComponent.getClass();
        UIComponentWriter<T> componentReader = classComponentHandlers.get(aClass);
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
    public void handleActivation(T out, UIComponent uiComponent, UIWriter<T> writer) {
        List<ActivationHandler<T>> activationHandlerList = activationHandlers.getOrDefault(uiComponent.getId(), Collections.emptyList());
        for (ActivationHandler<T> activationHandler : activationHandlerList) {
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

    private static class EmptyComponentReader implements UIComponentWriter {
        @Override
        public void write(Object out, UIComponent uiComponent, UIWriter writer) {
            // Nothing Intended
        }

        @Override
        public void dispose() {
            // Nothing Intended
        }
    }
}
