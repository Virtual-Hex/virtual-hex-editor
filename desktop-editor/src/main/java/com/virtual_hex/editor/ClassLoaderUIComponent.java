package com.virtual_hex.editor;

import com.virtual_hex.editor.data.AbstractUIComponent;

public class ClassLoaderUIComponent extends AbstractUIComponent {

    // todo once we start the plugin manager

    public String label;
    public ClassLoader classLoader;

    public ClassLoaderUIComponent() {
    }

    public ClassLoaderUIComponent(String label, ClassLoader classLoader) {
        this.label = label;
        this.classLoader = classLoader;
    }
}
