package com.mr00anderson.jawe.wrappers;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to dispose of the
public abstract class AbstractDataFieldMapper<T> implements DataFieldMapper<T> {

    // This is only use full for getting, any underlying changes to the nativeData structure would not trigger this,
    // so we need to setFieldFromNative the value from the class every call, then write it back every call
    protected T nativeData;
    protected Field field;
    protected Object object;

    public AbstractDataFieldMapper(T nativeData, Field field, Object object){
        this.nativeData = nativeData;
        this.field = field;
        this.object = object;
    }

    @Override
    public T getNativeData() {
        return nativeData;
    }

    @Override
    public void dispose() {
        nativeData = null;
        field = null;
        object = null;
    }
}
