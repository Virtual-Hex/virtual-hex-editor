package com.mr00anderson.jawe.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.mr00anderson.jawe.components.JaweRenderComponent;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.BasicApp;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoader;

// not completely generic
@All({JaweRenderComponent.class})
public class JaweRenderingSystem extends BaseEntitySystem {
    ComponentMapper<JaweRenderComponent> renderComponent;

    private transient JImGui imGui;
    private transient BasicApp app;

    public Int2ObjectMap<ActivationHandler> activationHandlers;

    @Override
    protected void initialize() {
        super.initialize();

        JniLoader.load();
        imGui = new JImGui();
        imGui.initBeforeMainLoop();

        activationHandlers = new Int2ObjectOpenHashMap<>();
        activationHandlers.put(0, new ActivationHandlerEmpty());

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
                JaweRenderComponent JaweRenderComponent = renderComponent.get(entityId);
                JaweRenderComponent.drawSafe(imGui, world);
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
        imGui.deallocateNativeObject();
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
        JaweRenderComponent.jaweDrawable.dispose();
    }

    public void  setMainApp(BasicApp app){
        this.app = app;
    }
}
