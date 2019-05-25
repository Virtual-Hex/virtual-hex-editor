package com.mr00anderson.editor.jimgui.wrappers;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to dispose of the
public abstract class AbstractDataFieldMapper<T> implements DataFieldMapper<T> {

    // This is only use full for getting, any underlying changes to the data structure would not trigger this,
    // so we need to setDataFromField the value from the class every call, then write it back every call
    protected T data;
    protected Field field;
    protected Object object;

    public AbstractDataFieldMapper(T data, Field field, Object object){
        this.data = data;
        this.field = field;
        this.object = object;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void dispose() {
        data = null;
        field = null;
        object = null;
    }
}
