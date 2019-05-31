package com.mr00anderson.jawe.components;

import com.artemis.BaseSystem;
import com.artemis.Component;
import com.artemis.ComponentType;
import com.artemis.utils.ImmutableBag;
import com.mr00anderson.jawe.JaweDesktopEditor;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.flag.JImSelectableFlags;

import java.util.Collections;
import java.util.LinkedList;

public class WorldJaweSelectable implements JaweDrawable {

    public JaweSelectable nameSelectable; // World name
    public JaweDrawable locationType;
    public JaweDrawable locationPath;

    public WorldJaweSelectable() {
    }

    public WorldJaweSelectable(WorldsJaweComponent worldsJaweComponent, WorldWrapper worldWrapper) {
        this.nameSelectable = new JaweSelectable(worldWrapper.name, JImSelectableFlags.SpanAllColumns);
        this.locationType = new JaweText(worldWrapper.someLocation.type.toString());
        this.locationPath = new JaweText(worldWrapper.someLocation.path);
        this.nameSelectable.onActivation = new ActivationHandler<JaweSelectable>() {

            @Override
            public void handle(JaweSelectable imGuiDrawable) {
                // Build entity and drop into world or remove it
                boolean selected = imGuiDrawable.selected.accessValue();
                if(selected){
                    BasicApp app = worldWrapper.renderingSystem.app;
                    if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                        // Check if exist
                       JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                       // Selectable reference


                        // SYSTEMS
                        ImmutableBag<BaseSystem> worldSystems = worldWrapper.world.getSystems();
                        LinkedList<JaweDrawable> systemsDrawables = new LinkedList<>();

                        Collections.addAll(
                                systemsDrawables,
                                JaweJImGui.SEPARATOR,
                                new JaweColumns("System - C", 3, true),
                                new JaweText("Class Name"),
                                JaweJImGui.NEXT_COLUMN,
                                new JaweText(("Enabled")), // Todo Check Box, are you sure pop up
                                JaweJImGui.NEXT_COLUMN,
                                new JaweText("Fully Qualified Name"),
                                JaweJImGui.NEXT_COLUMN,
                                JaweJImGui.SEPARATOR
                        );

                        for (int i = 0; i < worldSystems.size(); i++) {
                            BaseSystem baseSystem = worldSystems.get(i);
                            Class<? extends BaseSystem> aClass = baseSystem.getClass();
                            Collections.addAll(
                                        systemsDrawables,
                                        new JaweText(aClass.getSimpleName()),
                                        JaweJImGui.NEXT_COLUMN,
                                        new JaweText(String.valueOf(baseSystem.isEnabled())),// TODO Check box, ar you sure pop up
                                        JaweJImGui.NEXT_COLUMN,
                                        new JaweText(aClass.toString()),
                                        JaweJImGui.NEXT_COLUMN,
                                        JaweJImGui.SEPARATOR
                                    );
                        }
                        JaweDrawable[] jaweDrawablesSystem = new JaweDrawable[systemsDrawables.size()];
                        systemsDrawables.toArray(jaweDrawablesSystem);


                        // COMPONENT TYPES
                        ImmutableBag<ComponentType> componentTypes = worldWrapper.world.getComponentManager().getComponentTypes();
                        LinkedList<JaweDrawable> ComponentTypeDrawables = new LinkedList<>();

                        Collections.addAll(
                                ComponentTypeDrawables,
                                JaweJImGui.SEPARATOR,
                                new JaweColumns("Component - C", 4, true),
                                new JaweText("Class Name"),
                                JaweJImGui.NEXT_COLUMN,
                                new JaweText(("Index")),
                                JaweJImGui.NEXT_COLUMN,
                                new JaweText(("Is Pooled")),
                                JaweJImGui.NEXT_COLUMN,
                                new JaweText("Fully Qualified Name"),
                                JaweJImGui.NEXT_COLUMN,
                                JaweJImGui.SEPARATOR
                        );

                        for (int i = 0; i < componentTypes.size(); i++) {
                            ComponentType componentType = componentTypes.get(i);
                            Class<? extends Component> aClass = componentType.getType();
                            Collections.addAll(
                                    ComponentTypeDrawables,
                                    new JaweText(aClass.getSimpleName()),
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(String.valueOf(componentType.getIndex())),
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(String.valueOf(componentType.isPooled)),// TODO Check box, ar you sure pop up
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(aClass.toString()),
                                    JaweJImGui.NEXT_COLUMN,
                                    JaweJImGui.SEPARATOR
                            );
                        }

                        JaweDrawable[] jaweDrawablesComponents = new JaweDrawable[ComponentTypeDrawables.size()];
                        ComponentTypeDrawables.toArray(jaweDrawablesComponents);


                        JaweWindow jaweWindow = new JaweWindow(
                               worldWrapper.name,
                               new JaweText("TODO - World Meta Data"),
                               // TODO TABS
                               new JaweCollapsingHeader(
                                        "Systems",
                                               jaweDrawablesSystem
                               ),
                               new JaweCollapsingHeader(
                                       "Component Types",
                                            jaweDrawablesComponents
                               ),
                               new JaweCollapsingHeader("Entities"),
                               new JaweCollapsingHeader("Components")
                       );
                       worldsJaweComponent.worlds.putIfAbsent(nameSelectable.label, jaweWindow);
                       jaweDesktopEditor.getEditorWorldBuilder().addToWorld(jaweWindow);
                    } else {
                        // TODO ERROR should never happen as a worlds rendering system app should be a
                        // jawe editor here and nothing else
                        System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                    }
                } else {
                    BasicApp app = worldWrapper.renderingSystem.app;
                    if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                        JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                        jaweDesktopEditor.getEditorWorldBuilder().removeFromWorld(nameSelectable.label);
                        worldsJaweComponent.worlds.remove(nameSelectable.label);
                    } else {
                        // TODO ERROR should never happen as a worlds rendering system app should be a
                        // jawe editor here and nothing else
                        System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                    }
                }
            }
        };
    }

    @Override
    public void draw(JImGui imGui) {
        nameSelectable.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
        locationType.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
        locationPath.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
    }

//        new ActivationHandler<JaweSelectable>() {
//            @Override
//            public void handle(JaweSelectable imGuiDrawable) {
//                // Add a new entity with a component used to get to jaweDrawableArcheType mapper
//                // TODO make sure this window is not thrown away when the label is not selected because of creation and garbage,
//                // Only unload it when asked
////                        world.create()
//            }
}
