package com.virtual_hex.data;

import java.util.Map;

public interface UIDataDeserializer<T, D extends UIDataDeserializer> extends TypeDrawer<T, D>{
    UIDataDeserializer<T, D> newFromParent();
    Map<Class<?>, TypeDrawer<T, D>> getTypeDrawers();
    String getName();
    void init();
    void deallocateAll();
    void clearCache();
}
