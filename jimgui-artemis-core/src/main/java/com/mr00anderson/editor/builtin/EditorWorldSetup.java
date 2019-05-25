package com.mr00anderson.editor.builtin;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.ComponentMapper;
import com.artemis.World;
import com.mr00anderson.editor.atremis.components.JImGuiRenderComponent;
import com.mr00anderson.editor.atremis.components.MainMenuBarComponent;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import com.mr00anderson.editor.jimgui.JImGuiEditorClazzDrawableTest;
import com.mr00anderson.editor.types.JImGuiMenuItemDrawable;
import com.mr00anderson.editor.types.JImGuiWindowDrawable;
import com.mr00anderson.editor.utils.NativeUtils;

public class EditorWorldSetup {




    public static void setupEditorBaseEntities(final World world) {
        JImGuiDrawable[] mainMenuBar = {
                new JImGuiMenuItemDrawable("");
        };


        JImGuiDrawable[] components = {
                new MainMenuBarComponent(),// TODO dont forget debug window enable disable
                // Debug Window

                // Project browser ? This will be something eventually
                // World edit window - Selectable Worlds (project parents)
                // File browser (project parent), components, entities, prefabs can be loaded into a world or (STAGING Area)

                new JImGuiWindowDrawable("Clazz Draw Test", new JImGuiEditorClazzDrawableTest()),

        };

        Archetype archetype = new ArchetypeBuilder().add(JImGuiRenderComponent.class).build(world);
        ComponentMapper<JImGuiRenderComponent> mapper = world.getMapper(JImGuiRenderComponent.class);
        for (int i = 0; i < components.length; i++) {
            int entityId = world.create(archetype);
            JImGuiRenderComponent component = mapper.create(entityId);
            component.active = NativeUtils.createBool(true);
            component.jImGuiDrawable = components[i];
        }
    }

}
