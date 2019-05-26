package com.mr00anderson.jawe.builtin;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.mr00anderson.jawe.atremis.components.JaweRenderComponent;
import com.mr00anderson.jawe.atremis.components.MainMenuBarComponent;
import com.mr00anderson.jawe.jimgui.EditorDrawable;
import com.mr00anderson.jawe.jimgui.EditorEditorClazzDrawableTest;
import com.mr00anderson.jawe.types.EditorMenuItemDrawable;
import com.mr00anderson.jawe.types.EditorWindowDrawable;
import com.mr00anderson.jawe.utils.NativeUtils;

public class EditorWorldSetup {




    public static void setupEditorBaseEntities(final World world) {
        EditorDrawable[] mainMenuBar = {
                new EditorMenuItemDrawable("");
        };




        EditorDrawable[] components = {
                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)

                new EditorWindowDrawable("Clazz Draw Test", new EditorEditorClazzDrawableTest()),

        };

        Archetype archetype = new ArchetypeBuilder().add(JaweRenderComponent.class).build(world);
        ComponentMapper<JaweRenderComponent> mapper = world.getMapper(JaweRenderComponent.class);
        for (int i = 0; i < components.length; i++) {
            int entityId = world.create(archetype);
            JaweRenderComponent component = mapper.create(entityId);
            component.active = NativeUtils.createBool(true);
            component.editorDrawable = components[i];
        }
    }

}
