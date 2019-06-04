package com.mr00anderson.data;

public interface TypeDrawer<T> {
    void draw(T ui, Object drawable, ClazzDeserializer<T> parentDrawer);
}
