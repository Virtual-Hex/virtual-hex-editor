package com.mr00anderson.jawe;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.data.custom.*;
import com.mr00anderson.data.*;
import com.mr00anderson.jawe.types.WorldWrapper;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.flag.JImSelectableFlags;

public class EditorWorldBuilder {


    public static final String WORLD_EDITOR_WINDOW = "This Jawe Editor";
    public static final String WINDOW_DEBUG = "Debug Window";

    public final WorldWrapper worldWrapper;
    public final JaweJImGui imGui;
    public final Archetype jaweDrawableArcheType;
    public final ComponentMapper<JaweRenderComponent> renderMapper;
    public final TagManager tagManager;
    public final GroupManager groupManager;


    public EditorWorldBuilder(WorldWrapper worldWrapper, JaweJImGui imGui) {
        this.worldWrapper = worldWrapper;
        this.imGui = imGui;

        Window debugWindow = new Window(WINDOW_DEBUG, true, 0, new Drawables(new Text("TODO: Debug Window")));

        Window worldsWindow = new Window(
                WORLD_EDITOR_WINDOW,
                true,
                0,
                new Drawables(
                    new JaweColumnSet(
                        new JaweColumnSetHeader("Worlds", true,
                                "World", "Location Type", "Location Path"),
                        new JaweColumnSetBody(
                                new JaweColumnSetRow(
                                        new Object[]{
                                                new WorldSelectable(
                                                        worldWrapper.name,
                                                        false,
                                                        JImSelectableFlags.SpanAllColumns
                                                ),
                                                new Text(worldWrapper.someLocation.type.toString()),
                                                new Text(worldWrapper.someLocation.path)
                                        }
                                )
                        )
                    )
                )
        );

        Window inputWindow = new Window(
                "Input Int",
                true,
                0,
            new Drawables(
            new InputInt()
            )
        );

        // Register custom drawers
        JaweClazzDeserializer clazzDrawer = new JaweClazzDeserializer(true, "DEFAULT");
        clazzDrawer.typeDrawers.put(JaweColumnSet.class, EditorWorldBuilder::columnSet);
        clazzDrawer.typeDrawers.put(JaweColumnSetHeader.class, EditorWorldBuilder::columnSetHeader);
        clazzDrawer.typeDrawers.put(JaweColumnSetBody.class, EditorWorldBuilder::columnSetBody);
        clazzDrawer.typeDrawers.put(JaweColumnSetRow.class, EditorWorldBuilder::columnSetBodyRow);
        clazzDrawer.typeDrawers.put(WorldSelectable.class, JaweClazzDeserializer::selectable);



        jaweDrawableArcheType = new ArchetypeBuilder().add(JaweRenderComponent.class).build(worldWrapper.world);
        renderMapper = worldWrapper.world.getMapper(JaweRenderComponent.class);
        groupManager = worldWrapper.world.getSystem(GroupManager.class);
        tagManager = worldWrapper.world.getSystem(TagManager.class);

        addToWorld(debugWindow.label, clazzDrawer, debugWindow);
        addToWorld(worldsWindow.label, clazzDrawer, worldsWindow);
        addToWorld(inputWindow.label, clazzDrawer, inputWindow);

        // TODO add project load with worlds, worldWrapper settings


//        Drawable clearColor;
//        Drawable mainMenuBar;

        // TODO Replace with toggle menu option and update the eample using this libraires methods
//        Drawable debugWindow = Window.Builder
//                .builder()
//                .label("Debug")
//                .windowContents(new DebugWindow())
//                .build();
//

        // TODAY

        // Entity re-work
        // SO loop

        // TODO ADD
        // TODO REMOVE
        // TODO CATEGORIES (All (just paths), Unloaded, Loaded, Simulating)
        //        jaweButton = new Button();
//        jaweButton.label = "+";
//        jaweButton.onActivation = new ActivationHandler<Button>() {
//            @Override
//            public void handle(Button imGuiDrawable) {
//                // Draw temporary test input or file finder... TODO
//            }
//        };

        // TODO Load default editor but load saved worlds

//        // TODO Move this worldWrapper into the main menu for editing

//                new Window("Window",
//                        new ColorText("Test", new JImVec4(.5f,.5f,.5f,.5f)),
//                        new Text("Hello Test")
//                ),


        // DISABLED debug due to complexity, want to keep it simple for first worldWrapper edit testing and need for update use wit this libs api
//                debugWindow,


//                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
        // Debug Window

        // Project browser ? This will be something eventually
        // World edit window - Selectable Worlds (project parents)
        // File browser (project parent), components, entities, prefabs can be loaded into a worldWrapper or (STAGING Area)


    }


    public int addToWorld(String tag, ClazzDeserializer jaweClazzDeserializer, Object drawable){
        int entityId =  worldWrapper.world.create(jaweDrawableArcheType);
        JaweRenderComponent component = renderMapper.create(entityId);
        groupManager.add(entityId, "Drawable");
        tagManager.register(tag, entityId);
        component.active = true;
        component.jaweClazzDeserializer = jaweClazzDeserializer;
        component.drawable = drawable;
        return entityId;
    }

    // TODO TBD
    public void removeFromWorld(Window window){
        removeFromWorld(window.label);
    }

    public void removeFromWorld(String label) {
        int entitId = tagManager.getEntityId(label);
        if (entitId != -1) {
            worldWrapper.world.delete(entitId);
        }
    }

    private static void columnSet(JImGui imGui, Object drawable0, ClazzDeserializer parentDrawer) {
        JaweColumnSet drawable = (JaweColumnSet) drawable0;
        columnSetHeader(imGui, drawable.header, parentDrawer);
        columnSetBody(imGui, drawable.body, parentDrawer);
    }

    public static void columnSetHeader(JImGui imGui, Object drawable0, ClazzDeserializer parentDrawer){
        JaweColumnSetHeader drawable = (JaweColumnSetHeader) drawable0;
        int length = drawable.columns.length;
        imGui.columns(length, drawable.stringId, drawable.border);
        if(drawable.headerSeparatorTop) imGui.separator();
        for (int i = 0; i < length; i++) {
            Object column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
        if(drawable.headerSeparatorBottom) imGui.separator();
    }

    private static void columnSetBody(JImGui imGui, Object drawable0, ClazzDeserializer parentDrawer) {
        JaweColumnSetBody drawable = (JaweColumnSetBody) drawable0;
        int length = drawable.rows.length;
        for (int i = 0; i < length; i++) {
            Object column = drawable.rows[i];
            parentDrawer.draw(imGui, column, parentDrawer);
        }
    }

    private static void columnSetBodyRow(JImGui imGui, Object drawable0, ClazzDeserializer parentDrawer) {
        JaweColumnSetRow drawable = (JaweColumnSetRow) drawable0;
        int length = drawable.columns.length;
        for (int i = 0; i < length; i++) {
            Object column = drawable.columns[i];
            parentDrawer.draw(imGui, column, parentDrawer);
            imGui.nextColumn();
        }
    }
}
