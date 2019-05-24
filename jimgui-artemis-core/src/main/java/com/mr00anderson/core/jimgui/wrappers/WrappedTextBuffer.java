package com.mr00anderson.core.jimgui.wrappers;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to dispose of the
public class WrappedTextBuffer extends BufferAbstractDataFieldMapper {

    // Field then wrapped

    public WrappedTextBuffer(Field field, Object object) {
        this(new byte[255], field, object);
    }

    public WrappedTextBuffer(byte[] nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public String get() {
        return new String(data, 0, bufferEndIndex(data));
    }

    @Override
    public String getAsObject() {
        return get();
    }

    @Override
    public void setDataFromField() {
        Object o = null;
        try {
            o = field.get(object);
            String s = o.toString();
            copyStringIntoBuffer(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromData() {
        try {
            field.set(object, get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
