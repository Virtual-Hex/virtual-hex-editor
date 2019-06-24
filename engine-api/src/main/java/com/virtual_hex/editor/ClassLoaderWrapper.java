package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Label;

public class ClassLoaderWrapper extends Label {

    public ClassLoader classLoader;

    public ClassLoaderWrapper() {
    }

    public ClassLoaderWrapper(String label, ClassLoader classLoader) {
        super(label);
        this.classLoader = classLoader;
    }
}
