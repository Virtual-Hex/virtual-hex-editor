package com.mr00anderson.editor.atremis.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.utils.IntBag;
import com.mr00anderson.editor.BasicApp;
import com.mr00anderson.editor.atremis.components.JImGuiRenderComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.util.JniLoader;

// not completely generic
@All({JImGuiRenderComponent.class})
public class ImGuiEditorRenderingSystem extends BaseEntitySystem {
    ComponentMapper<JImGuiRenderComponent> renderComponent;

    private JImGui imGui;
    private BasicApp app;

    @Override
    protected void initialize() {
        super.initialize();

        JniLoader.load();
        imGui = new JImGui();
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
            for (int i = 0; i < entities.size(); i++) {
                int entityId = entities.get(i);
                JImGuiRenderComponent JImGuiRenderComponent = renderComponent.get(entityId);
                JImGuiRenderComponent.drawSafe(imGui, world);
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
        for (int i = 0; i < entities.size(); i++) {
            int entityId = entities.get(i);
            JImGuiRenderComponent JImGuiRenderComponent = renderComponent.get(entityId);
            JImGuiRenderComponent.jImGuiDrawable.dispose();
        }

        imGui.deallocateNativeObject();
    }


    public void  setMainApp(BasicApp app){
        this.app = app;
    }
}
