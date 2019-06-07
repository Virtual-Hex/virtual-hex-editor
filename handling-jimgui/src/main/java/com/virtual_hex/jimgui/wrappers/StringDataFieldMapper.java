package com.virtual_hex.jimgui.wrappers;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to close of the
public class StringDataFieldMapper extends BufferAbstractDataFieldMapper {

    // Field then wrapped

    public StringDataFieldMapper(Field field, Object object) {
        this(new byte[255], field, object);
    }

    public StringDataFieldMapper(byte[] nativeData, Field field, Object object) {
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
