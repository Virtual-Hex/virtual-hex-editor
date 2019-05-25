package com.mr00anderson.editor.jimgui.wrappers;

import com.mr00anderson.editor.Disposable;

public interface DataFieldMapper<T> extends Disposable {
    /**
     *
     * @return T underlying data source, may be a native reference class, byte[] or other
     */
    T getData();

    /**
     *
     * @return Object returns the data as a java object,
     * which may translate the data from native or byte[]
     * or other into
     */
    Object getAsObject();

    void setFieldFromData();
    void setDataFromField();
}
