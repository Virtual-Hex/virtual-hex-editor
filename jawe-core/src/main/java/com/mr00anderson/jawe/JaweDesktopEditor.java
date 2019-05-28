package com.mr00anderson.jawe;


import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.managers.WorldSerializationManager;
import com.mr00anderson.jawe.json.JaweJsonArtemisSerializer;
import com.mr00anderson.jawe.systems.JaweRenderingSystem;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.utils.ArtemisIoUtils;
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
 *              menu bar process can be a linked list of JaweDrawable's
 *
 */
public final class JaweDesktopEditor implements BasicApp {

    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(JaweDesktopEditor.class);

    public static final String WORLD_EDITOR_WINDOW = "This Jawe Editor";

    private World world;
    private boolean running = true;

    public static void main(String[] args) {
        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
        }
        JaweDesktopEditor jaweDesktopEditor = new JaweDesktopEditor();
        jaweDesktopEditor.run(null);
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
                .with(0, new JaweRenderingSystem())
                // TODO Profiler Plugin For JimGui
                // TODO Profiler Plugin For Editor ?
                .build();

        world = new World(worldConfig);

        // TODO BACKEND OPTION - LOADING HERE
        JsonArtemisSerializer jsonArtemisSerializer = new JaweJsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        Worlds.WORLDS.put(WORLD_EDITOR_WINDOW, world);

        // Set app here so that the ImGui instance can close this application
        JaweRenderingSystem system = world.getSystem(JaweRenderingSystem.class);
        system.setMainApp(this);

        // TODO Setup or load from a save file
        final boolean load = false;
        if(load){
            // TODO Editor serialization not supported yet
        } else {
            // Use builtin jawe setup
            EditorWorldSetup.setupEditorBaseEntities(world);
        }

        // We need to loop here, maybe allow a loop type to be chosen
        // The Imgui will be rendered through the main world view.
        while (running){
            world.process();
        }

        // Clean it up
        try {

            ArtemisIoUtils.saveAllFile(world, "jawe-cache-test.json");
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
