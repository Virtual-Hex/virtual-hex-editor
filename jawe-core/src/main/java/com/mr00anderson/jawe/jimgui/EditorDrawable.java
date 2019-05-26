package com.mr00anderson.jawe.jimgui;

import com.artemis.World;
import org.ice1000.jimgui.JImGui;

/**
 * This should represent the smallest unit translated to JImGui
 * This should map to JImGui#methodNameHere when created
 */
public interface EditorDrawable {

    EditorDrawable EMPTY_DRAWABLE = new EditorDrawable() {
        @Override
        public void draw(JImGui imGui, World world) {

        }

        @Override
        public void dispose() {

        }
    };

    void draw(JImGui imGui, World world);
    void dispose();
}
