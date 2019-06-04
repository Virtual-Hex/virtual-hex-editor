package com.virtual_hex.jimgui.wrappers;

import org.ice1000.jimgui.cpp.DeallocatableObject;

import java.lang.reflect.Field;

public abstract class AbstractDeallocateDataFieldMapper<T extends DeallocatableObject> extends AbstractDataFieldMapper<T> {

    public AbstractDeallocateDataFieldMapper(T nativeData, Field field, Object object) {
        super(nativeData, field, object);
    }

    @Override
    public void dispose() {
        nativeData.deallocateNativeObject();
        super.dispose();
    }
}
