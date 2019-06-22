package com.virtual_hex.editor.io;

import com.virtual_hex.editor.data.UIComponent;

public class RunnableActivationHandler<T> implements ActivationHandler<T> {

    public Runnable runnable;

    public RunnableActivationHandler(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void handle(T out, UIComponent objectActivated, UIWriter<T> parentDrawer) {
        runnable.run();
    }
}
