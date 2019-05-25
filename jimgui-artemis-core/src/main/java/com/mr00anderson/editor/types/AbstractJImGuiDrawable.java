package com.mr00anderson.editor.types;

import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import com.mr00anderson.editor.jimgui.handlers.ActivationHandler;

public abstract class AbstractJImGuiDrawable<T extends JImGuiDrawable> implements JImGuiDrawable {

    public ActivationHandler<T> activationHandler;

    public AbstractJImGuiDrawable() {
    }

    public AbstractJImGuiDrawable(ActivationHandler<T> activationHandler) {
        this.activationHandler = activationHandler;
    }
}
