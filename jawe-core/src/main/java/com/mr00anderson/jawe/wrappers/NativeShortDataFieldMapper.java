package com.mr00anderson.jawe.wrappers;

import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to dispose of the
public class NativeShortDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeShortDataFieldMapper(Field field, Object object) {
        super(new NativeInt(), field, object);
    }


    public NativeShortDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public short get() {
        return data.shortValue();
    }

    @Override
    public Short getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setShort(object, data.shortValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getInt(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
