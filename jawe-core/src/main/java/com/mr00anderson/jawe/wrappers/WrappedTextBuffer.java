package com.mr00anderson.jawe.wrappers;

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
        return new String(nativeData, 0, bufferEndIndex(nativeData));
    }

    @Override
    public String getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.set(object, get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setNativeFromField() {
        Object o = null;
        try {
            o = field.get(object);
            String s = o.toString();
            copyStringIntoBuffer(s);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


}
