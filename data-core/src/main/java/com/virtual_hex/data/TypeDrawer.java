package com.virtual_hex.data;

public interface TypeDrawer<T> {
    void draw(T ui, UIComponent uiComponent, UIDataDeserializer parentDeserializer);
}
