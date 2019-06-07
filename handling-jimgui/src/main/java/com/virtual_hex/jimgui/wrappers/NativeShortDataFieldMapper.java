package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to close of the
public class NativeShortDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeShortDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public short get() {
        return nativeData.shortValue();
    }

    @Override
    public Short getAsObject() {
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
            field.setShort(object, nativeData.shortValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
