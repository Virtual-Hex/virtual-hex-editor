package com.mr00anderson.jawe.drawables;

import com.artemis.annotations.PooledWeaver;
import com.mr00anderson.jawe.handlers.ActivationHandler;

@PooledWeaver
public abstract class AbstractJaweDrawable implements JaweDrawable {

    public ActivationHandler onActivation;

    protected void draw0(boolean b) {
        if(b) onActivation.handle( this);
    }
}
