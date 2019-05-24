package com.mr00anderson.core.jimgui.wrappers;

import org.ice1000.jimgui.NativeLong;

import java.lang.reflect.Field;


public class NativeLongDataFieldMapper extends AbstractDeallocateDataFieldMapper<NativeLong> {

    public NativeLongDataFieldMapper(NativeLong nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    public long get() {
        return data.longValue();
    }

    @Override
    public Long getAsObject() {
        return get();
    }

    @Override
    public void setFieldFromData() {
        try {
            field.setLong(object, data.longValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDataFromField() {
        try {
            data.modifyValue(field.getLong(object));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
