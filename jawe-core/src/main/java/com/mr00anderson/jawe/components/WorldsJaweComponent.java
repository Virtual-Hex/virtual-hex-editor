package com.mr00anderson.jawe.components;

import com.artemis.PooledComponent;
import com.artemis.World;
import com.mr00anderson.jawe.Worlds;
import com.mr00anderson.jawe.drawables.JaweButton;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweSelectable;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;

public class WorldsJaweComponent extends PooledComponent implements JaweDrawable {

    public JaweButton jaweButton;
    public Set<JaweSelectable> selectables;

    public WorldsJaweComponent() {
        this.jaweButton = new JaweButton();
        jaweButton.label = "+";
        jaweButton.onActivation = new ActivationHandler<JaweButton>() {
            @Override
            public void handle(JaweButton imGuiDrawable) {
                // Draw temporary test input or file finder... TODO
            }
        };
        this.selectables = new TreeSet<>(Comparator.comparing(o -> o.label));

        Worlds.WORLDS.forEach(new BiConsumer<String, World>() {
            @Override
            public void accept(String s, World world) {
                JaweSelectable jaweSelectable = new JaweSelectable(s);
                jaweSelectable.onActivation = new ActivationHandler<JaweSelectable>() {
                    @Override
                    public void handle(JaweSelectable imGuiDrawable) {
                        // Add a new entity with a component used to get to archetype mapper
                        // TODO make sure this window is not thrown away when the label is not selected because of creation and garbage,
                        // Only unload it when asked
//                        world.create()
                    }
                };
                selectables.add(jaweSelectable);
            }
        });

    }

    // TODO
    @Override
    protected void reset() {

    }

    @Override
    public void draw(JImGui imGui, World world) {
        // TODO ADD
        // TODO REMOVE

        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)

        for (JaweSelectable selectable : selectables) {
            selectable.draw(imGui, world);
        }


    }
}
