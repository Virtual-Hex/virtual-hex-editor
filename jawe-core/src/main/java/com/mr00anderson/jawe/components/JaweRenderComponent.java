package com.mr00anderson.jawe.components;

import com.artemis.Component;
import com.mr00anderson.jawe.drawables.JaweClazzDrawer;

/**
 * This can be anything that draws, but this is for uniform
 * mapping, since artemis requires using specific clazz types,
 * and no sub class mapping? Maybe do a PR to map for them
 * an extension that maps aspects of subtypes?
 */
public class JaweRenderComponent extends Component {

    /**
     * If the rendering component is active or not
     */
    public boolean active;

    /**
     * The component that should be drawn. This is used as the base parent drawer and layout for this component
     */
    public JaweClazzDrawer jaweClazzDrawer;

    /**
     * Cannot nest this in the JaweClazzDrawerWrapper because of the infinite recursion on serialization
     */
    public Object drawable;

    public JaweRenderComponent() {
    }
}
