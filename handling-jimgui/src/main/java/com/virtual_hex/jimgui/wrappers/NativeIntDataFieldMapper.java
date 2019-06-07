package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to close of the
public class NativeIntDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeIntDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public int get() {
        return nativeData.intValue();
    }

    @Override
    public Integer getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getInt(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setInt(object, nativeData.intValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
