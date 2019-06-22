package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;

public interface UIComponentWriter<T> {
    void write(T out, UIComponent uiComponent, UIWriter<T> writer);

    void dispose();
}
