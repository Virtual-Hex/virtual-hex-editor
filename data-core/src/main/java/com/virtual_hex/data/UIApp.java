package com.virtual_hex.data;

/**
 * Must be registered with ClazzDrawer
 */
public class UIApp implements UIData {

    public String title;
    public int width;
    public int height;
    public UIDeserializerWrapper deserializerWrapper;

    public UIApp() {
    }

    public UIApp(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public UIApp(String title, int width, int height, UIDeserializerWrapper deserializerWrapper) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.deserializerWrapper = deserializerWrapper;
    }
}
