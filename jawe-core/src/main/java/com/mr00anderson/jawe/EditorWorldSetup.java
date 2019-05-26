package com.mr00anderson.jawe;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.drawables.JaweClazzDraw;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.utils.JaweUtils;

public class EditorWorldSetup {




    public static void setupEditorBaseEntities(final World world) {
        buildMainMenu();

        JaweDrawable debugWindow = JaweWindow.JaweWindowBuilder
                .aJaweWindow()
                .label("Debug")
                .windowContents(new DebugWindow())
                .build();


        JaweDrawable clazzTestDrawWindow = JaweWindow.JaweWindowBuilder
                .aJaweWindow()
                .label("Clazz Test Draw 1")
                .windowContents(new JaweClazzDraw())
                .build();


        JaweDrawable clazzTestDrawWindow2 = JaweWindow.JaweWindowBuilder
                .aJaweWindow()
                .label("Clazz Test Draw 2")
                .windowContents(new JaweClazzDraw())
                .onActivation(new ActivationHandler<JaweWindow>() {
                    @Override
                    public void handle(JaweWindow imGuiDrawable) {
                        System.out.println(imGuiDrawable);
                    }

                    @Override
                    public void dispose() {
                        System.out.println("Disposing: " + this);
                    }
                })
                .build();


        JaweDrawable[] jaweDefaultBuildEntities = {
                debugWindow,
                clazzTestDrawWindow2,
                clazzTestDrawWindow
//                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)



        };



        Archetype archetype = new ArchetypeBuilder().add(JaweRenderComponent.class).build(world);
        ComponentMapper<JaweRenderComponent> mapper = world.getMapper(JaweRenderComponent.class);
        for (int i = 0; i < jaweDefaultBuildEntities.length; i++) {
            int entityId = world.create(archetype);
            JaweRenderComponent component = mapper.create(entityId);
            component.active = JaweUtils.createBool(true);
            component.jaweDrawable = jaweDefaultBuildEntities[i];
        }

    }

    private static void buildMainMenu() {

    }

}
