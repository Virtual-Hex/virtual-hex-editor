package com.mr00anderson.editor.types;

import com.mr00anderson.editor.jimgui.EditorDrawable;
import com.mr00anderson.editor.jimgui.handlers.ActivationHandler;

public class EditorWindowBuilder {

    public static final ActivationHandler<EditorWindowDrawable> EMPTY_HANDLER = drawable -> {};

    public String label;
    public boolean open = true;
    public int flags = 0;
    public EditorDrawable windowContents = EditorDrawable.EMPTY_DRAWABLE;
    public ActivationHandler<EditorWindowDrawable> onBeginActivation = EMPTY_HANDLER;

    public EditorWindowBuilder setLabel(String label) {
        this.label = label;
        return this;
    }

    public EditorWindowBuilder setClose() {
        this.open = false;
        return this;
    }

    public EditorWindowBuilder setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public EditorWindowBuilder setWindowContents(EditorDrawable windowContents) {
        this.windowContents = windowContents;
        return this;
    }

    public EditorWindowBuilder setOnBeginActivation(ActivationHandler<EditorWindowDrawable> onBeginActivation) {
        this.onBeginActivation = onBeginActivation;
        return this;
    }
}
