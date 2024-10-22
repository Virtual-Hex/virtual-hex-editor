package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;

public interface UIComponentReader<T> {
    UIComponent read(T in, UIReader<T> parentSerializer);

    void dispose();
}
