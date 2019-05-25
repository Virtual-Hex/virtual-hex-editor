package com.mr00anderson.editor.builtin;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.mr00anderson.editor.atremis.components.JImGuiRenderComponent;
import com.mr00anderson.editor.atremis.components.MainMenuBarComponent;
import com.mr00anderson.editor.jimgui.EditorDrawable;
import com.mr00anderson.editor.jimgui.EditorEditorClazzDrawableTest;
import com.mr00anderson.editor.types.EditorMenuItemDrawable;
import com.mr00anderson.editor.types.EditorWindowDrawable;
import com.mr00anderson.editor.utils.NativeUtils;

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

        Archetype archetype = new ArchetypeBuilder().add(JImGuiRenderComponent.class).build(world);
        ComponentMapper<JImGuiRenderComponent> mapper = world.getMapper(JImGuiRenderComponent.class);
        for (int i = 0; i < components.length; i++) {
            int entityId = world.create(archetype);
            JImGuiRenderComponent component = mapper.create(entityId);
            component.active = NativeUtils.createBool(true);
            component.editorDrawable = components[i];
        }
    }

}
