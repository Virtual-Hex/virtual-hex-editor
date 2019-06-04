package com.virtual_hex.jimgui.wrappers;

public interface DataFieldMapper<T> extends Disposable {
    /**
     *
     * @return T underlying nativeData source, may be a native reference class, byte[] or other
     */
    T getNativeData();

    /**
     *
     * @return Object returns the nativeData as a java object,
     * which may translate the nativeData from native or byte[]
     * or other into
     */
    Object getAsObject();

    void setNativeFromField();
    void setFieldFromNative();
}
