package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.NativeLong;

import java.lang.reflect.Field;


public class NativeLongDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeLong> {

    public NativeLongDataFieldMapper(NativeLong nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public long get() {
        return nativeData.longValue();
    }

    @Override
    public Long getAsObject() {
        return get();
    }

    @Override
    public void setNativeFromField() {
        try {
            nativeData.modifyValue(field.getLong(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFieldFromNative() {
        try {
            field.setLong(object, nativeData.longValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
