package com.virtual_hex.handling;

import com.virtual_hex.data.UIComponent;

public interface ActivationHandler<T> {

    /**
     * The drawable that was activated
     * @param objectActivated
     */
    void handle(UIComponent objectActivated, UIDeserializer<T> parentDrawer);
    void dispose();
}