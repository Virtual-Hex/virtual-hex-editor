package com.mr00anderson.core.jimgui;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

public interface JImGuiDrawable {
    void draw(JImGui imGui, World world);
    void dispose();
}
