package com.mr00anderson.editor;


import com.artemis.*;
import com.mr00anderson.editor.atremis.components.JImGuiRenderComponent;
import com.mr00anderson.editor.atremis.components.MainMenuBarComponent;
import com.mr00anderson.editor.atremis.systems.ImGuiEditorRenderingSystem;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import com.mr00anderson.editor.jimgui.JImGuiEditorClazzDrawableTest;

/**
 * TODO: Will need some reflective data entities to be able to render IDs to names, ect since
 * its a faster system, this will add some performance hits when added to the world only
 */
public class DesktopEditor implements BasicApp {

    public static final String WORLD_EDITOR_WINDOW = "This Editor";
    public static final String WORLDS_VIEW = "Loaded Worlds";
    private static final DesktopEditor DESKTOP_EDITOR = new DesktopEditor();

    private static World world = new World();
    private static boolean running = true;

    public static void main(String[] args) throws InterruptedException {
      DESKTOP_EDITOR.run();
    }

    private void run() {
        // World config is temp we should put a generic clicking screen for now to launch stuff, that can swap to other
        // screens
        // Load the world, Since this is the world we need to load the engine entities manually to get
        // stuff on this screen to render. Normally in game the world will be provided to the screen?

        // Load a world builder, this is where everything is needed to be added to process our engine components
        WorldConfiguration build = new WorldConfigurationBuilder()
                // keeps components available until all listeners have been called.
                // Use this if your systems need to access components to clean up after removal.
                .alwaysDelayComponentRemoval(true)
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

        world = new World(build);

        Worlds.WORLDS.put(WORLD_EDITOR_WINDOW, world);

        ImGuiEditorRenderingSystem system = world.getSystem(ImGuiEditorRenderingSystem.class);
        system.setMainApp(this);

        // TODO Setup or load from a save file
        setupEditorBaseEntities();


        // We need to loop here, maybe allow a loop type to be chosen
        // The Imgui will be rendered through the main world view.
        while (running){
            world.process();
        }

        // Clean it up
        world.dispose();
    }

    private void setupEditorBaseEntities() {
        JImGuiDrawable[] components = {
                new JImGuiEditorClazzDrawableTest(),
                new MainMenuBarComponent()
        };

        Archetype archetype = new ArchetypeBuilder().add(JImGuiRenderComponent.class).build(world);
        ComponentMapper<JImGuiRenderComponent> mapper = world.getMapper(JImGuiRenderComponent.class);
        for (int i = 0; i < components.length; i++) {
            int entityId = world.create(archetype);
            JImGuiRenderComponent component = mapper.create(entityId);
            component.active = true;
            component.jImGuiDrawable = components[i];
        }
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
