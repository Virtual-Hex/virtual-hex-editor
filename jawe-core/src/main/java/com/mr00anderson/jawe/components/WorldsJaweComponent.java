package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.JaweColumns;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweSelectable;
import com.mr00anderson.jawe.drawables.JaweText;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.WorldWrapper;
import com.mr00anderson.jawe.types.Worlds;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.flag.JImSelectableFlags;

import java.util.*;

// TODO Further abstraction optional, can take a list of elements and rename this to JaweDrawableComponent
public class WorldsJaweComponent extends PooledComponent implements JaweDrawable {

    public JaweOrderedDrawables drawables;

    public WorldsJaweComponent() {
        //        jaweButton = new JaweButton();
//        jaweButton.label = "+";
//        jaweButton.onActivation = new ActivationHandler<JaweButton>() {
//            @Override
//            public void handle(JaweButton imGuiDrawable) {
//                // Draw temporary test input or file finder... TODO
//            }
//        };

        JaweOrderedDrawables worldsList = new JaweOrderedDrawables();

        this.drawables = new JaweOrderedDrawables(
                new JaweColumns("Worlds Columns", 3, true),
                new JaweText("Worlds"),
                JaweJImGui.NEXT_COLUMN,
                new JaweText("Location Type"),
                JaweJImGui.NEXT_COLUMN,
                new JaweText("Location Path"),
                JaweJImGui.NEXT_COLUMN

        );


        Set<WorldWrapper> worldWrapperSet = new TreeSet<>(Comparator.comparing(o -> o.name));

        JaweDrawable[] drawables = new JaweDrawable[Worlds.WORLDS.size()];


        for (int i = 0; i < drawables.length; i++) {

        }


//        (worldWrapper -> {
//
//            InternalJaweDrawable internalJaweDrawable = new InternalJaweDrawable(worldWrapper);
//            drawables[index++];
//            worldsSet.add(internalJaweDrawable);
//        });

        // TODO ADD
        // TODO REMOVE

        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)

    }

    public class InternalJaweDrawable implements JaweDrawable{
        public String label;
        public JaweSelectable worldNameSelectable; // World name
        public JaweDrawable locationType;
        public JaweDrawable locationPath;
        public ActivationHandler<JaweSelectable> onActivation;

        public InternalJaweDrawable(WorldWrapper worldWrapper) {
            this.worldNameSelectable = new JaweSelectable(worldWrapper.name, JImSelectableFlags.SpanAllColumns);
        }



        @Override
        public void draw(JImGui imGui) {
            worldNameSelectable.draw(imGui);
            JaweJImGui.NEXT_COLUMN.draw(imGui);
            locationType.draw(imGui);
        }

//        new ActivationHandler<JaweSelectable>() {
//            @Override
//            public void handle(JaweSelectable imGuiDrawable) {
//                // Add a new entity with a component used to get to archetype mapper
//                // TODO make sure this window is not thrown away when the label is not selected because of creation and garbage,
//                // Only unload it when asked
////                        world.create()
//            }
    }

    // TODO
    @Override
    protected void reset() {

    }

    @Override
    public void draw(JImGui imGui) {
        drawables.draw(imGui);
    }
}
