package com.mr00anderson.jawe.handlers;

import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.types.Disposable;

/**
 * We want to pass through the handling to custom implementations, so we created ActivationHandler
 * to allow connecting of the handling, The caller is fed in, so that the person can access the
 * object that triggered the activation
 *
 * @param <T>
 */
public interface ActivationHandler<T extends JaweDrawable> extends Disposable {
    ActivationHandler<JaweWindow> EMPTY_DRAWABLE = new ActivationHandler<JaweWindow>() {
        @Override
        public void dispose() {

        }

        @Override
        public void handle(JaweWindow imGuiDrawable) {

        }
    };

    void handle(T imGuiDrawable);
}



// Activation wrapper is a instance where we need to inject empty value or check every time
// Could be done on entities add, or just a null check.

// For now will just use new instance of activation handler
