package com.virtual_hex.data;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UIDataDeserializer<T> implements TypeDrawer<T> {

    public String name;
    public Map<Class<?>, TypeDrawer<T>> typeDrawers;

    public UIDataDeserializer() {
        this.name = "Anonymous";
        this.typeDrawers = new HashMap<>();
    }

    public UIDataDeserializer(String name) {
        this.name = name;
        this.typeDrawers = new HashMap<>();
    }

    public UIDataDeserializer(String name, Map<Class<?>, TypeDrawer<T>> typeDrawers) {
        this.name = name;
        this.typeDrawers = typeDrawers;
    }


    public UIDataDeserializer(Function<Map<Class<?>, TypeDrawer<T>>, Map<Class<?>, TypeDrawer<T>>> typeDrawers) {
        this.name = "Anonymous";
        this.typeDrawers = typeDrawers.apply(new HashMap<>());
    }


    public UIDataDeserializer(String name, Function<Map<Class<?>, TypeDrawer<T>>, Map<Class<?>, TypeDrawer<T>>> typeDrawers) {
        this.name = name;
        this.typeDrawers = typeDrawers.apply(new HashMap<>());
    }


    @Override
    public void draw(T imGui, UIComponent uiComponent, UIDataDeserializer parentDeserializer) {
        Class<?> aClass = uiComponent.getClass();
        TypeDrawer<T> objectDrawer = typeDrawers.get(aClass);
        if (objectDrawer == null) {
            objectDrawer = checkSubtype(aClass.getSuperclass());
        }
//        LOGGER.debug("Drawing a class {}, type {}", drawable, aClass);
        objectDrawer.draw(imGui, uiComponent, parentDeserializer);
    }

    // Check iterations and sub-typing through testing
    public TypeDrawer<T> checkSubtype(Class<?> aSubTypeClazz) {
        // Try as a subtype instead later for this for generics
        TypeDrawer<T> biConsumer = typeDrawers.get(aSubTypeClazz);
        return biConsumer == null ? UIDataDeserializer::emptyDrawable : biConsumer;
    }

    private static <T> void emptyDrawable(T t, UIComponent uiComponent, UIDataDeserializer uiDataDeserializer) {
    }

    //    UIDataDeserializer<T> newFromParent(){
//
//    }
//
//
//
//
//    Map<Class<?>, TypeDrawer<T, D>> getTypeDrawers();
//
//    void init();
//    void deallocateAll();
//    void clearCache();
}
