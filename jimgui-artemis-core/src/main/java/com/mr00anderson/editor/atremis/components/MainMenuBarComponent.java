package com.mr00anderson.editor.atremis.components;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class MainMenuBarComponent implements JImGuiDrawable {

    private NativeBool nativeBool;

    @Override
    public void draw(JImGui imGui, World world) {
        if(nativeBool.accessValue()){

        }
    }

    @Override
    public void dispose() {
        nativeBool.deallocateNativeObject();
    }
}
