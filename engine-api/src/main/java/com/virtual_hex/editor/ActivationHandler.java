package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

public interface ActivationHandler<T> {

    /**
     * The uiComponent that was activated
     *
     * @param objectActivated
     */
    void handle(T out, UIComponent objectActivated, UIWriter<T> parentDrawer);
}