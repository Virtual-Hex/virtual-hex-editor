package com.mr00anderson.jawe;

import com.artemis.PooledComponent;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

import java.util.LinkedList;

public class WorldsJaweComponent extends PooledComponent implements JaweDrawable {


    public LinkedList<JaweDrawable> drawables;

    public WorldsJaweComponent() {
        drawables = new LinkedList<>();

        //        jaweButton = new JaweButton();
//        jaweButton.label = "+";
//        jaweButton.onActivation = new ActivationHandler<JaweButton>() {
//            @Override
//            public void handle(JaweButton imGuiDrawable) {
//                // Draw temporary test input or file finder... TODO
//            }
//        };

        JaweColumns jaweColumns = new JaweColumns();
        jaweColumns.stringId = "Worlds Columns";
        jaweColumns.count = 3;
        jaweColumns.border = true;
        drawables.add(jaweColumns);

        JaweDrawableText columnLabelOne = new JaweText();
        columnLabelOne.text = "Worlds";
        drawables.add(columnLabelOne);
        drawables.add(JaweJImGui.NEXT_COLUMN);

        JaweDrawableText columnLabelTwo = new JaweText();
        columnLabelTwo.text = "Location Type";
        drawables.add(columnLabelTwo);
        drawables.add(JaweJImGui.NEXT_COLUMN);

        JaweDrawableText columnLabelThree = new JaweText();
        columnLabelThree.text = "Location Path";
        drawables.add(columnLabelThree);
        drawables.add(JaweJImGui.NEXT_COLUMN);



//        worldsSet = new TreeSet<>(Comparator.comparing(o -> o.label));
//
//        Worlds.WORLDS.values().forEach(new Consumer<WorldWrapper>() {
//            @Override
//            public void accept(WorldWrapper worldWrapper) {
//                JawePairDrawable jawePairDrawable = new JawePairDrawable();
//                jawePairDrawable.jaweSelectable = new JaweSelectable(worldWrapper.name);
//                jawePairDrawable.
//                jawePairDrawable.onActivation = new ActivationHandler<JaweSelectable>() {
//                    @Override
//                    public void handle(JaweSelectable imGuiDrawable) {
//                        // Add a new entity with a component used to get to archetype mapper
//                        // TODO make sure this window is not thrown away when the label is not selected because of creation and garbage,
//                        // Only unload it when asked
////                        world.create()
//                    }
//                };
//                worldsSet.add(jawePairDrawable);
//            }
//        });

        // TODO ADD
        // TODO REMOVE

        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)

    }

    public class JawePairDrawable implements JaweDrawable{
        public String label;
        public JaweSelectable jaweSelectable;
        public JaweDrawable jaweDrawable;
        public ActivationHandler<JaweSelectable> onActivation;


        @Override
        public void draw(JImGui imGui) {
            jaweSelectable.draw(imGui);
            JaweJImGui.NEXT_COLUMN.draw(imGui);
            jaweDrawable.draw(imGui);
        }
    }

    // TODO
    @Override
    protected void reset() {

    }

    @Override
    public void draw(JImGui imGui) {
        drawables.forEach((d) -> d.draw(imGui));
    }
}
