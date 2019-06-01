package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.types.WorldWrapper;

import java.util.Map;
import java.util.TreeMap;

// TODO Further abstraction optional, can take a list of elements and rename this to JaweDrawableComponent
public class WorldsJaweComponent extends PooledComponent {

    public JaweOrderedDrawables worldsHeader;
    public JaweOrderedDrawables worldsData;

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

//        // TODO Move this world into the main menu for editing
        worldsData = new JaweOrderedDrawables(new WorldJaweSelectable(this, worldWrapper));

        worldsHeader = new JaweOrderedDrawables(
                // ADD
//                new JaweButton(" + ", new ActivationHandler<JaweButton>() {
//                    @Override
//                    public void handle(JaweButton imGuiDrawable) {
//                        new JaweWindow("Add World");
//                    }
//                }),

                new JaweColumns("Worlds Columns", 3, true),
                new JaweText("Worlds"),
                new JaweNextColumn(),
                new JaweText("Location Type"),
                new JaweNextColumn(),
                new JaweText("Location Path"),
                new JaweNextColumn(),
                new JaweSeparator()
        );
    }

    @Override
    protected void reset() {
        // TODO
    }
}
