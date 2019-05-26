package com.mr00anderson.jawe.types;

import com.artemis.World;
import com.mr00anderson.jawe.jimgui.EditorDrawable;
import org.ice1000.jimgui.JImGui;

public class EditorBeginMenuItemDrawable implements EditorDrawable {

    public String label;
    public boolean enabled;

    @Override
    public void draw(JImGui imGui, World world) {
        imGui.beginMenu(label, enabled);
    }

    @Override
    public void dispose() {

    }
}
