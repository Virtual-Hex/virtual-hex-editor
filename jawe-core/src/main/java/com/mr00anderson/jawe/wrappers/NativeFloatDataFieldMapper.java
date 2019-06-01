package com.mr00anderson.jawe.wrappers;

import com.mr00anderson.jawe.JaweStaticDeallocateManager;
import org.ice1000.jimgui.NativeFloat;

import java.lang.reflect.Field;

public class NativeFloatDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeFloat> {

    public NativeFloatDataFieldMapper(Field field, Object object) {
        super(JaweStaticDeallocateManager.createFloat(0.0f), field, object);
    }

    public NativeFloatDataFieldMapper(NativeFloat nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public float get() {
        return data.floatValue();
    }

    @Override
    public Float getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setFloat(object, data.floatValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getFloat(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

