package com.mr00anderson.editor;


import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.managers.WorldSerializationManager;
import com.mr00anderson.editor.artemis.json.EnhancedJsonArtemisSerializer;
import com.mr00anderson.editor.atremis.systems.ImGuiEditorRenderingSystem;
import com.mr00anderson.editor.builtin.EditorWorldSetup;
import com.mr00anderson.editor.utils.ArtemisIoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * TODO: Will need some reflective data entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 *
 * Note: As of now the intent is that Entities translate to a widget.
 *          -> Entities have a main render component
 *          -> Entities should be added in the order of rendering
 *          -> Entities will have one component for rendering and in that
 *              their may be a organization of how data is added, Example:
 *              menu bar process can be a linked list of EditorDrawable's
 *
 */
public final class DesktopEditor implements BasicApp {

    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(DesktopEditor.class);

    public static final String WORLD_EDITOR_WINDOW = "This Editor";

    private World world;
    private boolean running = true;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
        }
        DesktopEditor desktopEditor = new DesktopEditor();
        desktopEditor.run(null);
    }

    public void run(WorldConfigurationBuilder builder) {
        if(builder == null) {
            builder = new WorldConfigurationBuilder();
        }

        WorldSerializationManager manager = new WorldSerializationManager();

        WorldConfiguration worldConfig = builder.alwaysDelayComponentRemoval(true)
                .dependsOn()
                .with(manager)
                .with(new CoreEntityPlugin())
                .with(0, new ImGuiEditorRenderingSystem())
                // TODO Profiler Plugin For JimGui
                // TODO Profiler Plugin For Editor ?
                .build();

        world = new World(worldConfig);

        // TODO BACKEND OPTION - LOADING HERE
        JsonArtemisSerializer jsonArtemisSerializer = new EnhancedJsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        Worlds.WORLDS.put(WORLD_EDITOR_WINDOW, world);

        // Set app here so that the ImGui instance can close this application
        ImGuiEditorRenderingSystem system = world.getSystem(ImGuiEditorRenderingSystem.class);
        system.setMainApp(this);

        // TODO Setup or load from a save file
        final boolean load = false;
        if(load){
            // TODO Editor serialization not supported yet
        } else {
            // Use builtin editor setup
            EditorWorldSetup.setupEditorBaseEntities(world);
        }

        // We need to loop here, maybe allow a loop type to be chosen
        // The Imgui will be rendered through the main world view.
        while (running){
            world.process();
        }

        // Clean it up
        try {
            ArtemisIoUtils.saveAllFile(world, "editor-cache-test.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        world.dispose();
    }

    public World getWorld() {
        return world;
    }

    @Override
    public void setRunning() {
        this.running = true;
    }

    @Override
    public void setNotRunning() {
        this.running = false;
    }
}
