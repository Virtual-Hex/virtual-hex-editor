package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

public interface UIComponentWriter<T, E> {
    void write(T out, UIComponent uiComponent, E writer);

    void dispose();
}
