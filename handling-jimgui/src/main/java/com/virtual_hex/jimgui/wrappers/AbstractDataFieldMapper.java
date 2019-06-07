package com.virtual_hex.jimgui.wrappers;

import java.lang.reflect.Field;


// This class will be cachedNativeValue, so will need to close of the
public abstract class AbstractDataFieldMapper<T> implements DataFieldMapper<T> {

    // This is only use full for getting, any underlying changes to the nativeData structure would not trigger this,
    // so we need to setFieldFromNative the value from the class every call, then write it back every call
    protected transient T nativeData;
    protected transient Field field;
    protected transient Object object;

    public AbstractDataFieldMapper() {
    }

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
