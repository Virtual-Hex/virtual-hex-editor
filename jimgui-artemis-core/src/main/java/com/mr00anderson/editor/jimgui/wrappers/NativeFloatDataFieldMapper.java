package com.mr00anderson.editor.jimgui.wrappers;

import org.ice1000.jimgui.NativeFloat;

import java.lang.reflect.Field;

public class NativeFloatDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeFloat> {

    public NativeFloatDataFieldMapper(Field field, Object object) {
        super(new NativeFloat(), field, object);
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

