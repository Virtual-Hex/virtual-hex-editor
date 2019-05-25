package com.mr00anderson.editor.atremis.components;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import org.ice1000.jimgui.JImGui;

import java.util.Collections;
import java.util.LinkedList;

public class MainMenuBarComponent implements JImGuiDrawable {

    // Link List of Menu Items or array

    public LinkedList<JImGuiDrawable> linkedList;

    public MainMenuBarComponent() {
    }

    public MainMenuBarComponent(JImGuiDrawable... jImGuiDrawables){
        LinkedList<JImGuiDrawable> drawables = new LinkedList<>();
        Collections.addAll(drawables, jImGuiDrawables);
        this.linkedList = new LinkedList<>();
    }

    @Override
    public void draw(JImGui imGui, World world) {
        if(imGui.beginMainMenuBar()){
            linkedList.forEach(jImGuiDrawable -> jImGuiDrawable.draw(imGui, world));
            imGui.endMainMenuBar();
        }
    }

    @Override
    public void dispose() {

    }

}
