package com.mr00anderson.jawe.components;

import com.artemis.Component;
import com.mr00anderson.jawe.ClazzDrawer;
import com.mr00anderson.jawe.JaweJImGui;

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
    public ClazzDrawer clazzDrawer = new ClazzDrawer();

    public boolean active;

    /**
     * The component that should be drawn
     */
    public Object jaweDrawable;

    public JaweRenderComponent() {
    }

    public void draw(JaweJImGui imGui){
        if(active) clazzDrawer.draw(imGui, jaweDrawable);
    }


}
