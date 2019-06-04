package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;


public class NativeByteDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeByteDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public byte get() {
        return nativeData.byteValue();
    }

    @Override
    public Byte getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getByte(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setByte(object, nativeData.byteValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
