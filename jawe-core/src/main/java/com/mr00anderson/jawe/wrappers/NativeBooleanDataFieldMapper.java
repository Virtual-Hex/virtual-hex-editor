package com.mr00anderson.jawe.wrappers;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import org.ice1000.jimgui.NativeBool;

import java.lang.reflect.Field;


public class NativeBooleanDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeBool> {

    public NativeBooleanDataFieldMapper(Field field, Object object) {
        super(JaweStaticDeallocateManager.createBool(false), field, object);
    }

    public NativeBooleanDataFieldMapper(NativeBool nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public boolean get() {
        return nativeData.accessValue();
    }

    @Override
    public Boolean getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getBoolean(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setBoolean(object, nativeData.accessValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
