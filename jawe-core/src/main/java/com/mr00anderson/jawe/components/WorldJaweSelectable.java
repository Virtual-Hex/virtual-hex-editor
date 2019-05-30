package com.mr00anderson.jawe.components;

import com.mr00anderson.jawe.JaweDesktopEditor;
import com.mr00anderson.jawe.JaweJImGui;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import com.mr00anderson.jawe.drawables.JaweSelectable;
import com.mr00anderson.jawe.drawables.JaweText;
import com.mr00anderson.jawe.drawables.JaweWindow;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.types.BasicApp;
import com.mr00anderson.jawe.types.WorldWrapper;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.flag.JImSelectableFlags;

public class WorldJaweSelectable implements JaweDrawable {

    public JaweSelectable nameSelectable; // World name
    public JaweDrawable locationType;
    public JaweDrawable locationPath;

    public WorldJaweSelectable() {
    }

    public WorldJaweSelectable(WorldsJaweComponent worldsJaweComponent, WorldWrapper worldWrapper) {
        this.nameSelectable = new JaweSelectable(worldWrapper.name, JImSelectableFlags.SpanAllColumns);
        this.locationType = new JaweText(worldWrapper.someLocation.type.toString());
        this.locationPath = new JaweText(worldWrapper.someLocation.path);
        this.nameSelectable.onActivation = new ActivationHandler<JaweSelectable>() {

            @Override
            public void handle(JaweSelectable imGuiDrawable) {
                // Build entity and drop into world or remove it
                boolean selected = imGuiDrawable.selected.accessValue();
                if(selected){
                    BasicApp app = worldWrapper.renderingSystem.app;
                    if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                        // Check if exist
                       JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                       JaweWindow jaweWindow = new JaweWindow(nameSelectable.label, JaweJImGui.EMPTY_DRAWABLE);
                       worldsJaweComponent.worlds.putIfAbsent(nameSelectable.label, jaweWindow);
                       jaweDesktopEditor.getEditorWorldBuilder().addToWorld(jaweWindow);
                    } else {
                        // TODO ERROR should never happen as a worlds rendering system app should be a
                        // jawe editor here and nothing else
                        System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                    }
                } else {
                    BasicApp app = worldWrapper.renderingSystem.app;
                    if(app.getClass().isAssignableFrom(JaweDesktopEditor.class)){
                        JaweDesktopEditor jaweDesktopEditor = (JaweDesktopEditor) app;
                        jaweDesktopEditor.getEditorWorldBuilder().removeFromWorld(nameSelectable.label);
                        worldsJaweComponent.worlds.remove(nameSelectable.label);
                    } else {
                        // TODO ERROR should never happen as a worlds rendering system app should be a
                        // jawe editor here and nothing else
                        System.err.println("TODO ERROR JAWE SELECTABLE COULD NOT BE DRAWN");
                    }
                }
            }
        };
    }

    @Override
    public void draw(JImGui imGui) {
        nameSelectable.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
        locationType.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
        locationPath.draw(imGui);
        JaweJImGui.NEXT_COLUMN.draw(imGui);
    }

//        new ActivationHandler<JaweSelectable>() {
//            @Override
//            public void handle(JaweSelectable imGuiDrawable) {
//                // Add a new entity with a component used to get to jaweDrawableArcheType mapper
//                // TODO make sure this window is not thrown away when the label is not selected because of creation and garbage,
//                // Only unload it when asked
////                        world.create()
//            }
}
