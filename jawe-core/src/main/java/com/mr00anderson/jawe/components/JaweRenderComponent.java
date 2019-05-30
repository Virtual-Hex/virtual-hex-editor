package com.mr00anderson.jawe.components;

import com.artemis.Component;
import com.artemis.World;
import com.mr00anderson.jawe.drawables.JaweDrawable;
import org.ice1000.jimgui.JImGui;

/**
 * This can be anything that draws, but this is for uniform
 * mapping, since artemis requires using specific clazz types,
 * and no sub class mapping? Maybe do a PR to map for them
 * an extension that maps aspects of subtypes?
 */
public class JaweRenderComponent extends Component {

    /**
     * Used to indicate if a component should be drawn
     */
    public boolean active;

    /**
     * The component that should be drawn
     */
    public JaweDrawable jaweDrawable;

    public JaweRenderComponent() {
    }

    public void draw(JImGui imGui, World world){
        if(active) jaweDrawable.draw(imGui);
    }


}
