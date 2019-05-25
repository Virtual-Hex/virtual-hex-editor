package com.mr00anderson.editor;


import com.artemis.*;
import com.mr00anderson.editor.atremis.components.JImGuiRenderComponent;
import com.mr00anderson.editor.atremis.components.MainMenuBarComponent;
import com.mr00anderson.editor.atremis.systems.ImGuiEditorRenderingSystem;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import com.mr00anderson.editor.jimgui.JImGuiEditorClazzDrawableTest;
import com.mr00anderson.editor.jimgui.handlers.WorldProcessor;
import com.mr00anderson.editor.types.JImGuiWindowDrawable;
import com.mr00anderson.editor.utils.NativeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: Will need some reflective data entities to be able to render IDs to names, ect since
 * TODO: Pluggable Serialization
 *
 * Note: As of now the intent is that Entities translate to a widget.
 *          -> Entities have a main render component
 *          -> Entities should be added in the order of rendering
 *          -> Entities will have one component for rendering and in that
 *              their may be a organization of how data is added, Example:
 *              menu bar process can be a linked list of JImGuiDrawable's
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
        WorldProcessor worldProcessor = null;

        for (int i = 0; i < args.length; i++) {
            if(args[i].equals("--log-wp")){
                worldProcessor = (operation, world) -> L.debug("World Processor Called. Operation: {}, World {}", operation, world);
            }
        }
        DesktopEditor desktopEditor = new DesktopEditor();
        desktopEditor.run(null, worldProcessor == null ? (operation, world) -> {} : worldProcessor);
    }

    public void run(WorldConfigurationBuilder builder, WorldProcessor worldProcessor) {
        if(builder == null) {
            builder = new WorldConfigurationBuilder();
        }

        // World config is temp we should put a generic clicking screen for now to launch stuff, that can swap to other
        // screens
        // Load the world, Since this is the world we need to load the engine entities manually to get
        // stuff on this screen to render. Normally in game the world will be provided to the screen?

        // Load a world builder, this is where everything is needed to be added to process our engine components

                // keeps components available until all listeners have been called.
                // Use this if your systems need to access components to clean up after removal.
        WorldConfiguration worldConfig = builder.alwaysDelayComponentRemoval(true)
                .dependsOn()
                .with(
                        new CoreEntityPlugin()
                )
                // Describes dependencies on plugins. You can find more example plugins commented out in build.gradle.
//                .dependsOn(
                //EntityLinkManager.class,
                //OperationsPlugin.class,
//                        ProfilerPlugin.class)
                // This scene 2d system should
                // Since we do not have serialized version of this we will need to build out world stage parts probably here
                // before, if stage is not serializable then we need a asset and a loader for this use case
                .with(0, new ImGuiEditorRenderingSystem())
                .build();

        world = new World(worldConfig);
        worldProcessor.process(WorldProcessor.Operation.CREATED_NEW, world);

        Worlds.WORLDS.put(WORLD_EDITOR_WINDOW, world);

        ImGuiEditorRenderingSystem system = world.getSystem(ImGuiEditorRenderingSystem.class);
        system.setMainApp(this);

        // TODO Setup or load from a save file
        final boolean load = false;
        if(load){
            // TODO Editor serialization not supported yet
        } else {
            // Use default editor setup
            setupEditorBaseEntities();
        }


        // We need to loop here, maybe allow a loop type to be chosen
        // The Imgui will be rendered through the main world view.
        while (running){
            world.process();
        }

        // Clean it up
        worldProcessor.process(WorldProcessor.Operation.PRE_DISPOSE, world);
        world.dispose();
        worldProcessor.process(WorldProcessor.Operation.POST_DISPOSE, world);
    }

    private void setupEditorBaseEntities() {
        JImGuiDrawable[] components = {
                new MainMenuBarComponent(),
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
