package com.mr00anderson.jawe.components;

import com.artemis.Component;
import com.mr00anderson.jawe.drawers.jimgui.JaweClazzDrawer;
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
     *
     * This can be used to customize drawing by registering new classes
     */
    public JaweClazzDrawer clazzDrawer = new JaweClazzDrawer();

    /**
     * If the rendering component is active or not
     */
    public boolean active;

    /**
     * The component that should be drawn
     */
    public Object objectToDraw;

    public JaweRenderComponent() {
    }

    public void draw(JaweJImGui imGui){
        if(active) clazzDrawer.draw(imGui, objectToDraw);
    }


}
