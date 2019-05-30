package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.JaweColumns;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweText;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.types.WorldWrapper;
import org.ice1000.jimgui.JImGui;

import java.util.Map;
import java.util.TreeMap;

// TODO Further abstraction optional, can take a list of elements and rename this to JaweDrawableComponent
public class WorldsJaweComponent extends PooledComponent implements JaweDrawable {


    public JaweOrderedDrawables drawables;
    public JaweOrderedDrawables worldColumsDrawable;

    // Cached windows for when worlds are unclicked and clicked
    public Map<String, JaweWindow> worlds = new TreeMap<>(String::compareTo);

    public WorldsJaweComponent(WorldWrapper worldWrapper) {

        // TODO ADD
        // TODO REMOVE
        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)
        //        jaweButton = new JaweButton();
//        jaweButton.label = "+";
//        jaweButton.onActivation = new ActivationHandler<JaweButton>() {
//            @Override
//            public void handle(JaweButton imGuiDrawable) {
//                // Draw temporary test input or file finder... TODO
//            }
//        };

        // TODO Move this world into the main menu for editing
        worldColumsDrawable = new JaweOrderedDrawables(new WorldJaweSelectable(this, worldWrapper));

        this.drawables = new JaweOrderedDrawables(
                // ADD
//                new JaweButton(" + ", new ActivationHandler<JaweButton>() {
//                    @Override
//                    public void handle(JaweButton imGuiDrawable) {
//                        new JaweWindow("Add World");
//                    }
//                }),
                new JaweColumns("Worlds Columns", 3, true),
                new JaweText("Worlds"),
                JaweJImGui.NEXT_COLUMN,
                new JaweText("Location Type"),
                JaweJImGui.NEXT_COLUMN,
                new JaweText("Location Path"),
                JaweJImGui.NEXT_COLUMN,
                JaweJImGui.SEPARATOR
        );
    }

    @Override
    protected void reset() {
        // TODO
    }

    @Override
    public void draw(JImGui imGui) {
        drawables.draw(imGui);
        worldColumsDrawable.draw(imGui);
    }


}
