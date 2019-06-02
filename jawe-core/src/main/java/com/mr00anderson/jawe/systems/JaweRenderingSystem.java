package com.mr00anderson.jawe.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.types.BasicApp;
import org.ice1000.jimgui.util.JniLoader;

// not completely generic
@All({JaweRenderComponent.class})
public class JaweRenderingSystem extends BaseEntitySystem {
    ComponentMapper<JaweRenderComponent> renderComponent;

    public String title  = "TODO JaweRenderingSystem.class";
    public int width = 1280;
    public int height = 720;
    public transient JaweJImGui imGui;
    public transient BasicApp app;

    // TODO Insert a new entity dropper

    @Override
    public void inserted(IntBag entities) {
        super.inserted(entities);
        System.out.println(entities);
    }

    @Override
    protected void inserted(int entityId) {
        super.inserted(entityId);
        System.out.println(entityId);
        JaweRenderComponent jaweRenderComponent = renderComponent.get(entityId);
        world.inject(jaweRenderComponent);

        System.out.println(jaweRenderComponent.jaweClazzDrawer);

        // Components need to be injected but we want a functional interface for drawbles
        // Some worldsHeader care about the world and some do not


        // This is used for post construction logic, used to construct complex transient types
//        jaweRenderComponent.locationType.init(world);
    }


    @Override
    protected void initialize() {
        super.initialize();

        JniLoader.load();
        imGui = new JaweJImGui(width, height, title, world);// TODO Title some how before this is contructed or move this out
        imGui.initBeforeMainLoop();

        // TODO Clear color, should be a drawable saved in the world
    }

    @Override
        protected void begin() {
            super.begin();

        }
        @Override
        protected void processSystem() {
            if(imGui.windowShouldClose()){
                // TODO Saving
                app.setNotRunning();
                // Do not process anything else exit
                return;
            }
            imGui.initNewFrame();

            IntBag entities = subscription.getEntities();
            int length = entities.size();
            for (int i = 0; i < length; i++) {
                int entityId = entities.get(i);
                JaweRenderComponent jaweRenderComponent = renderComponent.get(entityId);
                jaweRenderComponent.draw(imGui);
            }

            imGui.render();
    }

    @Override
    protected void end() {
        super.end();
    }


    @Override
    protected void dispose() {
        super.dispose();
        // Depose of any objects
        IntBag entities = subscription.getEntities();
        removed(entities);
        imGui.deallocateNativeObject0();
    }

    @Override
    public void removed(IntBag entities) {
        for (int i = 0; i < entities.size(); i++) {
            int entityId = entities.get(i);
            removed(entityId);
        }
    }

    @Override
    protected void removed(int entityId) {
        super.removed(entityId);
        JaweRenderComponent JaweRenderComponent = renderComponent.get(entityId);
    }

    public void  setMainApp(BasicApp app, String title){
        this.app = app;
        this.title = title;

    }
}
