package com.mr00anderson.data.ext;


import com.mr00anderson.data.ClazzDeserializer;

/**
 * Used to wrap up a set of clazz drawers with its base
 */
public class JaweClazzDrawerWrapper<T> {

    /**
     * If the rendering component is active or not
     */
    public boolean active;

    /**
     * Can be a nest of object
     */
    public Object drawable;

    // External
    public void draw(T imGui, ClazzDeserializer<T> parentDrawer) {
        if(active) parentDrawer.draw(imGui, drawable, parentDrawer);
    }
}
