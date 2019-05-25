package com.mr00anderson.editor.jimgui;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

public interface JImGuiDrawable {
    void draw(JImGui imGui, World world);
    void dispose();
}
