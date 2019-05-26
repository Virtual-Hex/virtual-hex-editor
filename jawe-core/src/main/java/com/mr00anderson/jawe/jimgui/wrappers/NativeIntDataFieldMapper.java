package com.mr00anderson.jawe.jimgui.wrappers;

import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;

// This class will be cachedNativeValue, so will need to dispose of the
public class NativeIntDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeIntDataFieldMapper(Field field, Object object) {
        super(new NativeInt(), field, object);
    }


    public NativeIntDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public int get() {
        return data.intValue();
    }

    @Override
    public Integer getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setInt(object, data.intValue());
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
