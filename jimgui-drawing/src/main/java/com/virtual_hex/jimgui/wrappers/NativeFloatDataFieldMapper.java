package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeFloat;

import java.lang.reflect.Field;

public class NativeFloatDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeFloat> {

    public NativeFloatDataFieldMapper(NativeFloat nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public float get() {
        return nativeData.floatValue();
    }

    @Override
    public Float getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getFloat(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setFloat(object, nativeData.floatValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

