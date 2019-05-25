package com.mr00anderson.editor.jimgui;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

/**
 * This should represent the smallest unit translated to JImGui
 * This should map to JImGui#methodNameHere when created
 */
public interface JImGuiDrawable {
    void draw(JImGui imGui, World world);
    void dispose();
}
