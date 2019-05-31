package com.mr00anderson.jawe.components;

import com.artemis.*;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.JaweDesktopEditor;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.flag.JImSelectableFlags;

import java.util.Collections;
import java.util.LinkedList;
import java.util.function.IntConsumer;

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

                        // TODO Periodic refresh
//                        - Fix column issue with trees


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
                                        new JaweText(aClass.getCanonicalName()),
                                        JaweJImGui.NEXT_COLUMN
                                    );
                        }
                        JaweDrawable[] jaweDrawablesSystem = new JaweDrawable[systemsDrawables.size()];
                        systemsDrawables.toArray(jaweDrawablesSystem);


                        // COMPONENT TYPES
                        ImmutableBag<ComponentType> componentTypes = worldWrapper.world.getComponentManager().getComponentTypes();
                        LinkedList<JaweDrawable> componentTypeDrawables = new LinkedList<>();

                        Collections.addAll(
                                componentTypeDrawables,
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
                                    componentTypeDrawables,
                                    new JaweText(aClass.getSimpleName()),
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(String.valueOf(componentType.getIndex())),
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(String.valueOf(componentType.isPooled)),// TODO Check box, ar you sure pop up
                                    JaweJImGui.NEXT_COLUMN,
                                    new JaweText(aClass.getCanonicalName()),
                                    JaweJImGui.NEXT_COLUMN
                            );
                        }

                        JaweDrawable[] jaweDrawablesComponents = new JaweDrawable[componentTypeDrawables.size()];
                        componentTypeDrawables.toArray(jaweDrawablesComponents);



                        // TODO Insert Filtering option, or  wrap this in a filter, this way can edit groups or find entities of X composition
                        // Entities All
                        EntitySubscription entitySubscription = worldWrapper.world.getAspectSubscriptionManager().get(Aspect.all());
                        LinkedList<JaweDrawable> entityDrawables = new LinkedList<>();

//
//                        // TODO This is dynamic based on entity components
//                        Collections.addAll(
//                                entityDrawables,
//                                JaweJImGui.SEPARATOR,
//                                new JaweColumns("Entity - C", 4, true),
//                                new JaweText("Id"),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText(("")),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText(("Is Pooled")),
//                                JaweJImGui.NEXT_COLUMN,
//                                new JaweText("Fully Qualified Name"),
//                                JaweJImGui.NEXT_COLUMN,
//                                JaweJImGui.SEPARATOR
//                        );


                        IntBag entities = entitySubscription.getEntities();
                        int[] entityIds = entities.getData();

                        Int2ObjectMap<IntList> map = new Int2ObjectOpenHashMap<>();

                        // TODO Arch Type Mapper look up for friendly name mapping
                        for (int i = 0; i < entities.size(); i++) {
                            int entityId = entityIds[i];
                            int compositionIdentity = worldWrapper.world.getComponentManager().getIdentity(entityId);
                            IntList integers = map.computeIfAbsent(compositionIdentity, IntArrayList::new);
                            integers.add(entityId);
                        }

                        map.forEach((k, v) -> {
                            JaweTreeNodeExNoPop archetypeTree = new JaweTreeNodeExNoPop(String.format("Archetype ID: %d", k));
                            entityDrawables.add(archetypeTree);  // TODO Temp until String name for archetype

                           // The reason this is not working is because they are all drawing if not open,
                            // need to nest the drawables or rework the three class?, likely nest
                            v.forEach((IntConsumer) value -> {
                                JaweTreeNodeExNoPop entityTree = new JaweTreeNodeExNoPop(String.format("Entity ID: %d", value));
                                archetypeTree.drawables = new LinkedList<>();
                                archetypeTree.drawables.add(entityTree);

                                Bag<Component> components = new Bag<>();
                                worldWrapper.world.getComponentManager().getComponentsFor(value, components);
                                for (int i = 0; i < components.size(); i++) {
                                    Component component = components.get(i);

                                    JaweTreeNodeExNoPop componentTree = new JaweTreeNodeExNoPop(String.format("Component %s", component));
                                    entityTree.drawables = new LinkedList<>();
                                    entityTree.drawables.add(componentTree);

                                    componentTree.drawables = new LinkedList<>();
                                    componentTree.drawables.add(new JaweText("Todo Place Holder Clazz Drawer"));

                                    componentTree.drawables.add(
                                            // Pop Components Tree
                                            JaweJImGui.TREE_POP);
                                }
                                // Pop Entities Tree
                                entityDrawables.add(JaweJImGui.TREE_POP);
                            });
                            // Pop Archetype Tree
                            entityDrawables.add(JaweJImGui.TREE_POP);
                        });





                        JaweDrawable[] jaweDrawablesEntities = new JaweDrawable[entityDrawables.size()];
                        entityDrawables.toArray(jaweDrawablesEntities);


                        JaweWindow jaweWindow = new JaweWindow(
                               worldWrapper.name,
                               new JaweText("TODO - World Meta Data"),
                               // TODO TABS
                               new JaweCollapsingHeader(
                                        "Systems",
                                               jaweDrawablesSystem
                               ),
                               new JaweColumns("", 1, false),
                               new JaweCollapsingHeader(
                                       "Component Types",
                                            jaweDrawablesComponents
                               ),
                               new JaweColumns("", 1, false),
                               new JaweCollapsingHeader(
                                       "Entities",
                                       jaweDrawablesEntities
                               ),
                               new JaweColumns("", 1, false)
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
