package com.mr00anderson.jawe;


import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.io.JsonArtemisSerializer;
import com.artemis.managers.WorldSerializationManager;
import com.mr00anderson.jawe.components.SomeLocation;
import com.mr00anderson.jawe.json.JaweJsonArtemisSerializer;
import com.mr00anderson.jawe.systems.JaweRenderingSystem;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import com.mr00anderson.jawe.types.Worlds;
import com.mr00anderson.jawe.utils.ArtemisIoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.mr00anderson.jawe.components.SomeLocation.Type.CODE;

/**
 * TODO: Will need some reflective nativeData entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 *
 * Note: As of now the intent is that Entities translate to a widget.
 *          -> Entities have a main render component
 *          -> Entities should be added in the order of rendering
 *          -> Entities will have one component for rendering and in that
 *              their may be a organization of how nativeData is added, Example:
 *              menu bar process can be a linked list of Drawable's
 *
 *
 *
 * When editor is loaded...
 * project = worlds // May be more then one project
 * worlds = entities
 * entities = component
 *
 *  TODO- Replace linked list with fixed but resizable array to reduced linked list garbage
 */
public final class JaweDesktopEditor implements BasicApp {

    /**
     * Simply a Logger Reference
     */
    private static final Logger L = LoggerFactory.getLogger(JaweDesktopEditor.class);

    public static final String WORLD_EDITOR_WINDOW = "This Jawe Editor";

    public static JaweDesktopEditor INSTANCE;

    private World world;
    private boolean running = true;
    private EditorWorldBuilder editorWorldBuilder;
    private Worlds worlds = new Worlds();

    public static void main(String[] args) {
        // TODO jimgui.ini loading so users can import layouts
        for (int i = 0; i < args.length; i++) {
        }
        INSTANCE = new JaweDesktopEditor();
        INSTANCE.run(null);
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

        // Entities in this world will be generally entities with Drawable components
        world = new World(worldConfig);

        // TODO BACKEND OPTION - LOADING HERE
        JsonArtemisSerializer jsonArtemisSerializer = new JaweJsonArtemisSerializer(world).prettyPrint(true);
        manager.setSerializer(jsonArtemisSerializer);

        // Set app here so that the ImGui instance can close this application
        JaweRenderingSystem jaweRenderingSystem = world.getSystem(JaweRenderingSystem.class);
        jaweRenderingSystem.setMainApp(this, "JImGui Artemis obd World Editor (Jawe)");

        // TODO Optional load editor project, to allow editing a separate world for making a new version of the editor
        // this one will be serialized

        WorldWrapper worldWrapper = new WorldWrapper(WORLD_EDITOR_WINDOW, new SomeLocation(CODE, ""), world, jaweRenderingSystem);
        worlds.worlds.put(WORLD_EDITOR_WINDOW, worldWrapper);

        // TODO Setup or load from a save file
        final boolean load = false;
        if(load){
            // TODO Editor serialization not supported yet
        } else {
            // Use builtin jawe setup
            editorWorldBuilder = new EditorWorldBuilder(worldWrapper, jaweRenderingSystem.imGui);
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
        JaweStaticDeallocateManager.deallocateNativeObject0();
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

    public boolean isRunning() {
        return running;
    }

    public EditorWorldBuilder getEditorWorldBuilder() {
        return editorWorldBuilder;
    }
}
