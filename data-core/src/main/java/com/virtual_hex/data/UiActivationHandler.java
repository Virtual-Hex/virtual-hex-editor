package com.virtual_hex.data;

/**
 * We want to pass through the handling to custom implementations, so we created UiActivationHandler
 * to allow connecting of the handling, The caller is fed in, so that the person can access the
 * object that triggered the activation
 *
 * @param <T>
 */
@Deprecated // TODO verify usage and if needed
public interface UiActivationHandler<T> {

    // Not sure if this is really needed
    /**
     * The drawable that was activated
     * @param objectActivated
     */
    void handle(T objectActivated, UIDataDeserializer parentDrawer);
}



// Activation wrapper is a instance where we need to inject empty value or check every time
// Could be done on entities add, or just a null check.

// For now will just use new instance of activation handler
