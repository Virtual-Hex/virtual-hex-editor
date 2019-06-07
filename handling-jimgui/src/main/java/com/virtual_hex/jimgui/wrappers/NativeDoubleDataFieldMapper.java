package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeDouble;

import java.lang.reflect.Field;


public class NativeDoubleDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeDouble> {

    public NativeDoubleDataFieldMapper(NativeDouble nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public double get() {
        return nativeData.doubleValue();
    }

    @Override
    public Double getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getDouble(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setDouble(object, nativeData.doubleValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

