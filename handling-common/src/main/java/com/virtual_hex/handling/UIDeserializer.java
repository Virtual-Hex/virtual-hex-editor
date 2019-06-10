package com.virtual_hex.handling;

import com.virtual_hex.data.UIComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

/**
 *
 * @param <T> UI interface
 */
public class UIDeserializer<T>  {

    public String name;
    public Map<UUID, ComponentHandler<T>> uuidSpecificTypeHandlers;
    public Map<Class<?>, ComponentHandler<T>> classComponentHandlers;
    public Map<UUID, ActivationHandler<T>> activationHandlers;

    // TODO sub Typing

    public UIDeserializer() {
        this.name = "Anonymous";
        this.classComponentHandlers = new HashMap<>();
    }

    public UIDeserializer(String name) {
        this.name = name;
        this.classComponentHandlers = new HashMap<>();
    }

    public UIDeserializer(String name, Map<Class<?>, ComponentHandler<T>> classComponentHandlers) {
        this.name = name;
        this.classComponentHandlers = classComponentHandlers;
    }


    public UIDeserializer(Consumer<Map<Class<?>, ComponentHandler<T>>> typeDrawersConsumer) {
        this.name = "Anonymous";
        this.classComponentHandlers = new HashMap<>();
        typeDrawersConsumer.accept(this.classComponentHandlers);
    }


    public UIDeserializer(String name, Consumer<Map<Class<?>, ComponentHandler<T>>> typeDrawersConsumer) {
        this.name = name;
        this.classComponentHandlers = new HashMap<>();
        typeDrawersConsumer.accept(this.classComponentHandlers);
    }


    public void draw(T imGui, UIComponent uiComponent) {
        ComponentHandler<T> componentHandler = uuidSpecificTypeHandlers.get(uiComponent.id);
        if(componentHandler == null){
            componentHandler = checkType(uiComponent);
            if(componentHandler == null) return; // TODO Error or ?
        }

        componentHandler.draw(imGui, uiComponent, this);
    }

    private ComponentHandler<T> checkType(UIComponent uiComponent) {
        Class<?> aClass = uiComponent.getClass();
        ComponentHandler<T> componentHandler = classComponentHandlers.get(aClass);
        if(componentHandler == null){
            // Subtype Checking
        }
        return componentHandler;
    }

    /**
     * This is used to take action on widget activation, like a button or selection
     *
     * @param uiComponent
     * @param parentDeserializer
     */
    public void handle(UIComponent uiComponent, UIDeserializer parentDeserializer) {
        ActivationHandler<?> activationHandler = activationHandlers.get(uiComponent);
        if(activationHandler != null){
            activationHandler.handle(uiComponent, parentDeserializer);
        }
    }

    // TODO Register handlers


    /**
     * Dispose of resources here, WE may have a cache clear method for memory management if needed
     */
    public void disopse(){
        uuidSpecificTypeHandlers.forEach((k, v) -> v.dispose());
        classComponentHandlers.forEach((k, v) -> v.dispose());
        activationHandlers.forEach((k, v) -> v.dispose());
    }

}
