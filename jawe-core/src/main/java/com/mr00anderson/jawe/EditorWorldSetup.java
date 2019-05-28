package com.mr00anderson.jawe;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.drawables.*;

public class EditorWorldSetup {




    public static void setupEditorBaseEntities(final World world, final JaweJImGui imGui) {
        buildMainMenu();

//        JaweDrawable clearColor;
//        JaweDrawable mainMenuBar;

        JaweWindow worlds = new JaweWindow();
        worlds.label = "Worlds";
        worlds.windowContents = new WorldsJaweComponent();
        worlds.open.modifyValue(true);

        JaweWindow testWindow = new JaweWindow();
        testWindow.label = "Window";
        testWindow.windowContents = new JaweTestingWindow();
        testWindow.open.modifyValue(true);


        // TODO Replace with toggle menu option and update the eample using this libraires methods
//        JaweDrawable debugWindow = JaweWindow.Builder
//                .builder()
//                .label("Debug")
//                .windowContents(new DebugWindow())
//                .build();
//

        JaweDrawable[] jaweDefaultBuildEntities = {
                worlds,
                testWindow,


                // DISABLED debug due to complexity, want to keep it simple for first world edit testing and need for update use wit this libs api
//                debugWindow,


//                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)



        };



        Archetype archetype = new ArchetypeBuilder().add(JaweRenderComponent.class).build(world);
        ComponentMapper<JaweRenderComponent> mapper = world.getMapper(JaweRenderComponent.class);
        GroupManager system = world.getSystem(GroupManager.class);
        for (int i = 0; i < jaweDefaultBuildEntities.length; i++) {
            int entityId = world.create(archetype);
            JaweRenderComponent component = mapper.create(entityId);
            system.add(entityId, "JaweDrawable");
            component.active = true;
            component.jaweDrawable = jaweDefaultBuildEntities[i];
        }

    }

    private static void buildMainMenu() {

    }

}
