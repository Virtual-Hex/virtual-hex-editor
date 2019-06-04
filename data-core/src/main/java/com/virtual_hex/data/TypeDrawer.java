package com.virtual_hex.data;

public interface TypeDrawer<T, D> {
    void draw(T ui, UIData uiData, D parentDeserializer);
}
