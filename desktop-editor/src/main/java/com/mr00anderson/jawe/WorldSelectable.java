package com.mr00anderson.jawe;

import com.artemis.*;
import com.artemis.utils.Bag;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.mr00anderson.data.custom.*;
import com.mr00anderson.data.*;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;

public class WorldSelectable extends Selectable<WorldSelectable> {

    public static transient Map<String, Window> worldsCache = new HashMap<>();

    public WorldSelectable() {
        super();
        onActivation = WorldSelectable::handle;
    }

    public WorldSelectable(String label) {
        super(label, WorldSelectable::handle);
    }

    public WorldSelectable(String label, int flags) {
        super(label, flags, WorldSelectable::handle);
    }

    public WorldSelectable(String label, boolean selected) {
        super(label, selected, WorldSelectable::handle);
    }

    public WorldSelectable(String label, boolean selected, int flags) {
        super(label, selected, flags, WorldSelectable::handle);
    }

    public static void handle(WorldSelectable objectActivated, ClazzDeserializer parentDrawer) {
        WorldWrapper worldWrapper = JaweDesktopEditor.WORLDS.get(objectActivated.label);
        BasicApp app = worldWrapper.renderingSystem.app;
        // Check if exist
        JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;

         // Build entity and drop into worldWrapper or remove it
        if (objectActivated.selected) {
            Window drawable = worldsCache.computeIfAbsent(
                    objectActivated.label,
                    s -> {

                        // Selectable reference

                        // SYSTEMS
                        ImmutableBag<BaseSystem> worldSystems = worldWrapper.world.getSystems();

                        JaweColumnSetRow[] systems = new JaweColumnSetRow[worldSystems.size()];
                        for (int i = 0; i < worldSystems.size(); i++) {
                            BaseSystem baseSystem = worldSystems.get(i);
                            Class<? extends BaseSystem> aClass = baseSystem.getClass();
                            systems[i] = new JaweColumnSetRow(
                                    aClass.getSimpleName(),
                                    String.valueOf(baseSystem.isEnabled()),
                                    aClass.getCanonicalName()
                            );
                        }

                        JaweColumnSet systemColumns = new JaweColumnSet(
                                new JaweColumnSetHeader("System - C", true,
                                        "Class Name", "Enabled", "Fully Qualified Name"),
                                new JaweColumnSetBody(systems)
                        );


                        // COMPONENT TYPES
                        ImmutableBag<ComponentType> componentTypes = worldWrapper.world.getComponentManager().getComponentTypes();

                        JaweColumnSetRow[] componentRows = new JaweColumnSetRow[componentTypes.size()];
                        for (int i = 0; i < componentTypes.size(); i++) {
                            ComponentType componentType = componentTypes.get(i);
                            Class<? extends Component> aClass = componentType.getType();
                            componentRows[i] = new JaweColumnSetRow(
                                    aClass.getSimpleName(),
                                    String.valueOf(componentType.getIndex()),
                                    String.valueOf(componentType.isPooled),// TODO Check box, ar you sure pop up
                                    aClass.getCanonicalName()
                            );
                        }

                        JaweColumnSet componentColumns = new JaweColumnSet(
                                new JaweColumnSetHeader("Component - C", true,
                                        "Class Name", "Index", "Is Pooled", "Fully Qualified Name"),
                                new JaweColumnSetBody(componentRows)
                        );


                        // TODO Insert Filtering option, or  wrap this in a filter, this way can edit groups or find entities of X composition
                        // Entities All
                        EntitySubscription entitySubscription = worldWrapper.world.getAspectSubscriptionManager().get(Aspect.all());

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

                        EditableClazzDraw jaweClazzDraw = new EditableClazzDraw();

                        Drawables entityTree = new Drawables();
                        map.forEach((archetypes, entityIdsV) -> {
                            Drawables archtypeDrawable = new Drawables();


                            entityIdsV.forEach((IntConsumer) entityId -> {

                                Bag<Component> components = new Bag<>();
                                worldWrapper.world.getComponentManager().getComponentsFor(entityId, components);

                                Drawables componentsTree = new Drawables();

                                for (int i = 0; i < components.size(); i++) {
                                    Component component = components.get(i);
                                    // This could cause bigger serialization as this is loaded as a window, while
                                    // fields should be data mappers?
                                    componentsTree.drawables.add(new TreeNodeEx(
                                            String.format("Component %s", component),
                                            0,
                                            new Drawables(
                                                    new JaweColumnSet(
                                                            new JaweColumnSetHeader("", true, "Value & Field Name", "Type", "Size (b)"),
                                                            new JaweColumnSetBody()
                                                    )
                                            ) // We want to draw non transient, public fields // TODO methods to be used to transform

                                            // We can use the clazz drawer and register editable types?
                                    ));
                                }

                                archtypeDrawable.drawables.add(new TreeNodeEx(
                                        String.format("Entity ID: %d", entityId),
                                        0,  new Drawables(componentsTree)));
                            });
                            entityTree.drawables.add(
                                    new TreeNodeEx(
                                            String.format("Archetype ID: %d", archetypes),
                                            0,
                                            new Drawables(archtypeDrawable)));// TODO PR for JIMGUI to have a nothings flag like the rest of the API// TODO Temp until String name for archetype
                        });


                        return new Window(
                                worldWrapper.name,
                                true,
                                0,
                                new Drawables(
                                        new Text("TODO - World Meta Data"),
                                        new TabBar("World Editing", 0,
                                                new Drawables(
                                                        new BeginTabItem(
                                                                "Systems",
                                                                new Drawables(systemColumns)),
                                                        new Columns("", 1, false),
                                                        new BeginTabItem(
                                                                "Component Types",
                                                                new Drawables(componentColumns)),
                                                        new Columns("", 1, false),
                                                        new BeginTabItem("Entities", entityTree),
                                                        new Columns("", 1, false)
                                                )
                                        )
                                )
                        );
                    }
            );
            jaweDesktopEditor.getEditorWorldBuilder().addToWorld(worldWrapper.name, parentDrawer, drawable);
        } else {
            jaweDesktopEditor.getEditorWorldBuilder().removeFromWorld(worldWrapper.name);
        }

    }

}
