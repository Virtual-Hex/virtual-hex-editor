package com.mr00anderson.jawe.wrappers;

import org.ice1000.jimgui.NativeDouble;

import java.lang.reflect.Field;


public class NativeDoubleDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeDouble> {

    public NativeDoubleDataFieldMapper(Field field, Object object) {
        super(new NativeDouble(), field, object);
    }

    public NativeDoubleDataFieldMapper(NativeDouble nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public double get() {
        return data.doubleValue();
    }

    @Override
    public Double getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setDouble(object, data.doubleValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getDouble(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

