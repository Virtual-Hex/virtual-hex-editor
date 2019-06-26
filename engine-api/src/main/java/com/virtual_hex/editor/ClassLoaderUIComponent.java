package com.virtual_hex.editor;

import com.virtual_hex.editor.data.Label;

public class ClassLoaderUIComponent extends Label {

    // todo once we start the plugin manager

    public ClassLoader classLoader;

    public ClassLoaderUIComponent() {
    }

    public ClassLoaderUIComponent(String label, ClassLoader classLoader) {
        super(label);
        this.classLoader = classLoader;
    }
}
