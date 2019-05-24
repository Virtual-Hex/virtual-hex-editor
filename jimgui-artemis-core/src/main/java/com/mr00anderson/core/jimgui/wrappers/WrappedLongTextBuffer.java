package com.mr00anderson.core.jimgui.wrappers;

import java.lang.reflect.Field;


public class WrappedLongTextBuffer extends BufferAbstractDataFieldMapper {

    public WrappedLongTextBuffer(Field field, Object object) {
        super(field, object);
    }

    public WrappedLongTextBuffer(byte[] nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }


    @Override
    public void setFieldFromData() {
        try {
            field.setLong(object, get());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        long aLong = 0;
        try {
            aLong = field.getLong(object);
            String string = String.valueOf(aLong);
            copyStringIntoBuffer(string);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public long get() {
        String string = new String(data, 0, bufferEndIndex(data));
        return Long.parseLong(string);
    }

    @Override
    public Long getAsObject() {
        return get();
    }

}
