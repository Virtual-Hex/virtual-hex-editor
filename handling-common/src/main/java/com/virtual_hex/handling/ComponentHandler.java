package com.virtual_hex.handling;

import com.virtual_hex.data.UIComponent;

public interface ComponentHandler<T> {
    void draw(T ui, UIComponent uiComponent, UIDeserializer<T> parentDeserializer);
}
