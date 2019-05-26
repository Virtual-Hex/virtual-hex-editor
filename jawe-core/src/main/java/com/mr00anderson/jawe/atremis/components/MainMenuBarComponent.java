package com.mr00anderson.jawe.atremis.components;

import com.artemis.World;
import com.mr00anderson.jawe.jimgui.EditorDrawable;
import org.ice1000.jimgui.JImGui;

import java.util.Collections;
import java.util.LinkedList;

public class MainMenuBarComponent implements EditorDrawable {

    // Link List of Menu Items or array

    public LinkedList<EditorDrawable> linkedList;

    public MainMenuBarComponent() {
    }

    public MainMenuBarComponent(EditorDrawable... editorDrawables){
        LinkedList<EditorDrawable> drawables = new LinkedList<>();
        Collections.addAll(drawables, editorDrawables);
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
