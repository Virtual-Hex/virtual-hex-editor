package com.mr00anderson.editor.atremis.components;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.EditorDrawable;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class JImGuiRenderComponent extends DisposableComponent {

    public NativeBool active;
    public EditorDrawable editorDrawable;

    public JImGuiRenderComponent() {
    }

    public void drawSafe(JImGui imGui, World world){
        if(active.accessValue()) editorDrawable.draw(imGui, world);
    }

    @Override
    public void dispose() {
        editorDrawable.dispose();
    }
}
