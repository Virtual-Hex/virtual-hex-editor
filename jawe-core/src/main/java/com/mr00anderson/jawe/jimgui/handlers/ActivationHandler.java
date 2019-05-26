package com.mr00anderson.jawe.jimgui.handlers;

import com.mr00anderson.jawe.jimgui.EditorDrawable;

@FunctionalInterface
public interface ActivationHandler<T extends EditorDrawable> {
    void handle(T imGuiDrawable);
}
