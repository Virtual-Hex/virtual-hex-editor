package com.mr00anderson.jawe;

import com.artemis.*;
import com.artemis.utils.ImmutableBag;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.drawables.*;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.ice1000.jimgui.JImGui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.function.Consumer;

public class JaweWorldSelectable extends JaweSelectable<JaweWorldSelectable> {

    public static transient Map<String, JaweClazzDrawer> worldsCache = new HashMap<>();

    public JaweWorldSelectable() {
        super();
        onActivation = JaweWorldSelectable::handle;
    }

    public JaweWorldSelectable(String label) {
        super(label, JaweWorldSelectable::handle);
    }

    public JaweWorldSelectable(String label, int flags) {
        super(label, flags, JaweWorldSelectable::handle);
    }

    public JaweWorldSelectable(String label, boolean selected) {
        super(label, selected, JaweWorldSelectable::handle);
    }

    public JaweWorldSelectable(String label, boolean selected, int flags) {
        super(label, selected, flags, JaweWorldSelectable::handle);
    }

    public static void handle(JaweWorldSelectable objectActivated, JaweClazzDrawer parentDrawer) {
        WorldWrapper worldWrapper = JaweDesktopEditor.WORLDS.get(objectActivated.label);
        BasicApp app = worldWrapper.renderingSystem.app;
        // Check if exist
        JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;

         // Build entity and drop into worldWrapper or remove it
        if (objectActivated.selected) {
            JaweClazzDrawer clazzDrawer = worldsCache.computeIfAbsent(
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

                        JaweColumnSetRow[] components = new JaweColumnSetRow[componentTypes.size()];
                        for (int i = 0; i < componentTypes.size(); i++) {
                            ComponentType componentType = componentTypes.get(i);
                            Class<? extends Component> aClass = componentType.getType();
                            components[i] = new JaweColumnSetRow(
                                    aClass.getSimpleName(),
                                    String.valueOf(componentType.getIndex()),
                                    String.valueOf(componentType.isPooled),// TODO Check box, ar you sure pop up
                                    aClass.getCanonicalName()
                            );
                        }

                        JaweColumnSet componentColumns = new JaweColumnSet(
                                new JaweColumnSetHeader("Component - C", true,
                                        "Class Name", "Index", "Is Pooled", "Fully Qualified Name"),
                                new JaweColumnSetBody(components)
                        );


                        // TODO Insert Filtering option, or  wrap this in a filter, this way can edit groups or find entities of X composition
                        // Entities All
                        EntitySubscription entitySubscription = worldWrapper.world.getAspectSubscriptionManager().get(Aspect.all());
                        LinkedList<Consumer<JImGui>> entityDrawables = new LinkedList<>();

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

                        ReflectiveJaweClazzDraw jaweClazzDraw = new ReflectiveJaweClazzDraw();

                        map.forEach((archetypes, entityIdsV) -> {
//                                    JaweTreeNodeExNoPop archetypeTree = new JaweTreeNodeExNoPop(String.format("Archetype ID: %d", archetypes));
//                                    // Archtype Id Tree Pop
//                                    Collections.addAll(entityDrawables, archetypeTree, JaweJImGui.TREE_POP); // TODO Temp until String name for archetype
//
//                                    entityIdsV.forEach((IntConsumer) entityId -> {
//                                        JaweTreeNodeExNoPop entityTree = new JaweTreeNodeExNoPop(String.format("Entity ID: %d", entityId));
//
//                                        Collections.addAll(archetypeTree.worldsHeader, entityTree);
//
//                                        Bag<Component> components = new Bag<>();
//                                        worldWrapper.worldWrapper.getComponentManager().getComponentsFor(entityId, components);
//
//                                        for (int i = 0; i < components.size(); i++) {
//                                            Component component = components.get(i);
//                                            JaweTreeNodeExNoPop componentsTree = new JaweTreeNodeExNoPop(
//                                                    String.format("Component %s", component),
//                                                    // UPDATE CLAZZ DRAW AND ALSO SO
////                                                imGui -> jaweClazzDraw.drawSlowJavaClazz(imGui, entityId, component),
//                                                    JaweJImGui.TREE_POP
//                                            );
//                                            // Entity Id Tree Pop
//                                            Collections.addAll(entityTree.worldsHeader, componentsTree, JaweJImGui.TREE_POP);
//                                        }
//                                    });
                        });

                        return parentDrawer.newFromParent(
                                new JaweWindow(
                                        worldWrapper.name,
                                        true,
                                        0,
                                        new JaweDrawables(
                                                new JaweText("TODO - World Meta Data"),
                                                new JaweTabBar("World Editing", 0,
                                                        new JaweDrawables(
                                                                new JaweBeginTabItem(
                                                                        "Systems",
                                                                        new JaweDrawables(systemColumns)
                                                                ),
                                                                new JaweColumns("", 1, false),
                                                                new JaweBeginTabItem(
                                                                        "Component Types",
                                                                        new JaweDrawables(componentColumns)
                                                                ),
                                                                new JaweColumns("", 1, false),
                                                                new JaweBeginTabItem(
                                                                        "Entities",
                                                                        new JaweDrawables(
                                                                                entityDrawables
                                                                        )
                                                                ),
                                                                new JaweColumns("", 1, false)
                                                        )
                                                )
                                        )
                                )
                        );
                    }
            );
            jaweDesktopEditor.getEditorWorldBuilder().addToWorld(worldWrapper.name, clazzDrawer);
        } else {
            jaweDesktopEditor.getEditorWorldBuilder().removeFromWorld(worldWrapper.name);
        }

    }

}
