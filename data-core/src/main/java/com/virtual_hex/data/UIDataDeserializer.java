package com.virtual_hex.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class UIDataDeserializer<T> implements ComponentHandler<T> {

    public String name;
    public Map<Class<?>, ComponentHandler<T>> typeDrawers;

    public UIDataDeserializer() {
        this.name = "Anonymous";
        this.typeDrawers = new HashMap<>();
    }

    public UIDataDeserializer(String name) {
        this.name = name;
        this.typeDrawers = new HashMap<>();
    }

    public UIDataDeserializer(String name, Map<Class<?>, ComponentHandler<T>> typeDrawers) {
        this.name = name;
        this.typeDrawers = typeDrawers;
    }


    public UIDataDeserializer(Consumer<Map<Class<?>, ComponentHandler<T>>> typeDrawersConsumer) {
        this.name = "Anonymous";
        this.typeDrawers = new HashMap<>();
        typeDrawersConsumer.accept(this.typeDrawers);
    }


    public UIDataDeserializer(String name, Consumer<Map<Class<?>, ComponentHandler<T>>> typeDrawersConsumer) {
        this.name = name;
        this.typeDrawers = new HashMap<>();
        typeDrawersConsumer.accept(this.typeDrawers);
    }


    @Override
    public void draw(T imGui, UIComponent uiComponent, UIDataDeserializer parentDeserializer) {
        Class<?> aClass = uiComponent.getClass();
        ComponentHandler<T> objectDrawer = typeDrawers.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
//        LOGGER.debug("Drawing a class {}, type {}", drawable, aClass);
        objectDrawer.draw(imGui, uiComponent, parentDeserializer);
    }

    // Check iterations and sub-typing through testing
    public ComponentHandler<T> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        ComponentHandler<T> biConsumer = typeDrawers.get(aSubTypeClazz);
        return biConsumer == null ? UIDataDeserializer::emptyDrawable : biConsumer;
    }

    private static <T> void emptyDrawable(T ui, UIComponent uiComponent, UIDataDeserializer uiDataDeserializer) {
    }

    public void regiesterTypeDrawers(){

    }
    //    UIDataDeserializer<T> newFromParent(){
//
//    }
//
//
//
//
//    Map<Class<?>, ComponentHandler<T, D>> getTypeDrawers();
//
//    void init();
//    void deallocateAll();
//    void clearCache();
}
