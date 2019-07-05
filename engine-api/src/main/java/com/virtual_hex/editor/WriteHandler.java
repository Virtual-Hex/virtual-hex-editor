package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

public interface WriteHandler<T, E> {
    void handle(T out, UIComponent uiComponent, E writer);
}
