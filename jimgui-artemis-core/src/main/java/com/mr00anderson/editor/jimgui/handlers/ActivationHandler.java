package com.mr00anderson.editor.jimgui.handlers;

import com.mr00anderson.editor.jimgui.EditorDrawable;

@FunctionalInterface
public interface ActivationHandler<T extends EditorDrawable> {
    void handle(T imGuiDrawable);
}
