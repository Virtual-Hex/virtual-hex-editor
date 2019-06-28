package com.virtual_hex.editor;

import java.net.URL;

public class ClassHolder {

    public Class<?> aClass;
    public URL urlToLoad;

    public ClassHolder(Class<?> aClass, URL urlToLoad) {
        this.aClass = aClass;
        this.urlToLoad = urlToLoad;
    }
}
