package com.mr00anderson.editor.types;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.EditorDrawable;
import com.mr00anderson.editor.jimgui.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class EditorWindowDrawable extends AbstractEditorDrawable {

    public String label;
    public NativeBool open;
    public int flags;
    public EditorDrawable windowContents;
    public ActivationHandler<EditorWindowDrawable> onBeginActivation;

    public EditorWindowDrawable() {
    }

    @Override
    public void draw(JImGui imGui, World world) {
        boolean begin = imGui.begin(label, open, flags);
        if(begin) onBeginActivation.handle(this);
        windowContents.draw(imGui, world);
        imGui.end();
    }

    @Override
    public void dispose() {

    }
}
