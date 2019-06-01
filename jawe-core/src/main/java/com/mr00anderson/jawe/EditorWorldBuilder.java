package com.mr00anderson.jawe;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.components.WorldsJaweComponent;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.types.WorldWrapper;

public class EditorWorldBuilder {


    public final WorldWrapper worldWrapper;
    public final JaweJImGui imGui;
    public final Archetype jaweDrawableArcheType;
    public final ComponentMapper<JaweRenderComponent> renderMapper;
    public final TagManager tagManager;
    public final GroupManager groupManager;

    public EditorWorldBuilder(WorldWrapper worldWrapper, JaweJImGui imGui) {
        this.worldWrapper = worldWrapper;
        this.imGui = imGui;



        buildMainMenu();

//        JImGuiDrawable clearColor;
//        JImGuiDrawable mainMenuBar;

        // TODO Replace with toggle menu option and update the eample using this libraires methods
//        JImGuiDrawable debugWindow = JaweWindow.Builder
//                .builder()
//                .label("Debug")
//                .windowContents(new DebugWindow())
//                .build();
//

        JaweWindow[] jaweDefaultBuildEntities = {
                new JaweWindow("Worlds", new WorldsJaweComponent(worldWrapper)),
//                new JaweWindow("Window",
//                        new JaweColorText("Test", new JImVec4(.5f,.5f,.5f,.5f)),
//                        new JaweText("Hello Test")
//                ),


                // DISABLED debug due to complexity, want to keep it simple for first world edit testing and need for update use wit this libs api
//                debugWindow,


//                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)



        };


        jaweDrawableArcheType = new ArchetypeBuilder().add(JaweRenderComponent.class).build(worldWrapper.world);
        renderMapper = worldWrapper.world.getMapper(JaweRenderComponent.class);
        groupManager = worldWrapper.world.getSystem(GroupManager.class);
        tagManager = worldWrapper.world.getSystem(TagManager.class);
        for (int i = 0; i < jaweDefaultBuildEntities.length; i++) {
            addToWorld(jaweDefaultBuildEntities[i]);
        }

    }

    public int addToWorld(JaweWindow window){
        int entityId =  worldWrapper.world.create(jaweDrawableArcheType);
        JaweRenderComponent component = renderMapper.create(entityId);
        groupManager.add(entityId, "JImGuiDrawable");
        tagManager.register(window.label, entityId);
        component.active = true;
        component.objectToDraw = window;
        return entityId;
    }

    // TODO TBD
    public void removeFromWorld(JaweWindow window){
        removeFromWorld(window.label);
    }

    public void removeFromWorld(String label) {
        int entitId = tagManager.getEntityId(label);
        worldWrapper.world.delete(entitId);
    }

    private void buildMainMenu() {

    }


}
