package com.virtual_hex.editor;

import com.virtual_hex.editor.data.UIComponent;

public class RunnableActivationHandler<T, E> implements ActivationHandler<T, E> {

    public Runnable runnable;

    public RunnableActivationHandler(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void handle(T out, UIComponent objectActivated, E parentDrawer) {
        runnable.run();
    }

}
