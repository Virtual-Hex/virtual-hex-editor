package com.virtual_hex.data;

public interface ComponentHandler<T> {
    void draw(T ui, UIComponent uiComponent, UIDataDeserializer parentDeserializer);
}
