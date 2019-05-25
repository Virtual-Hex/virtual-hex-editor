package com.mr00anderson.editor.jimgui.wrappers;

import org.ice1000.jimgui.NativeBool;

import java.lang.reflect.Field;


public class NativeBooleanDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeBool> {

    public NativeBooleanDataFieldMapper(Field field, Object object) {
        super(new NativeBool(), field, object);
    }

    public NativeBooleanDataFieldMapper(NativeBool nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public boolean get() {
        return data.accessValue();
    }

    @Override
    public Boolean getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setBoolean(object, data.accessValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getBoolean(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
