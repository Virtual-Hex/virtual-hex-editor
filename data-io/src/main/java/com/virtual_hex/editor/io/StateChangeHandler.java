package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;

public interface StateChangeHandler<T> {

    /**
     * The uiComponent thats state changed
     *
     * @param objectChanged
     */
    void handle(T out, UIComponent objectChanged, UIWriter<T> parentDrawer);
}