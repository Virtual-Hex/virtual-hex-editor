package com.mr00anderson.jawe.components;

import com.artemis.World;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

/**
 * This can be anything that draws, but this is for uniform
 * mapping, since artemis requires using specific clazz types,
 * and no sub class mapping? Maybe do a PR to map for them
 * an extension that maps aspects of subtypes?
 */
public class JaweRenderComponent extends DisposableComponent {

    public NativeBool active;
    public JaweDrawable jaweDrawable;

    public JaweRenderComponent() {
    }

    public void drawSafe(JImGui imGui, World world){
        if(active.accessValue()) jaweDrawable.draw(imGui, world);
    }

    @Override
    public void dispose() {
        jaweDrawable.dispose();
    }


}
