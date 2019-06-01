package com.mr00anderson.jawe.wrappers;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import org.ice1000.jimgui.NativeInt;

import java.lang.reflect.Field;


public class NativeByteDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeInt> {

    public NativeByteDataFieldMapper(Field field, Object object) {
        super(JaweStaticDeallocateManager.createInt(0), field, object);
    }

    public NativeByteDataFieldMapper(NativeInt nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public byte get() {
        return data.byteValue();
    }

    @Override
    public Byte getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setByte(object, data.byteValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getByte(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
