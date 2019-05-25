package com.mr00anderson.editor.types;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;

public class JImGuiBeginMenuItemDrawable implements JImGuiDrawable {

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
