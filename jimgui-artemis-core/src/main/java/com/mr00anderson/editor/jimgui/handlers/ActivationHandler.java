package com.mr00anderson.editor.jimgui.handlers;

import com.mr00anderson.editor.jimgui.JImGuiDrawable;

public interface ActivationHandler<T extends JImGuiDrawable> {
    public boolean handle(T imGuiDrawable);
}
