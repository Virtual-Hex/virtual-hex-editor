package com.mr00anderson.data;

import java.util.Map;

public interface ClazzDeserializer<T> {
    ClazzDeserializer<T> newFromParent();
    Map<Class<?>, TypeDrawer<T>> getTypeDrawers();
    String getName();
    void init();
    void draw(T ui, Object drawable, ClazzDeserializer<T> parentDrawer);
}
