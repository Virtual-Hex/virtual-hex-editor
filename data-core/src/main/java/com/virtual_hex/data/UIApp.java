package com.virtual_hex.data;

/**
 * Must be registered with ClazzDrawer
 */
public class UIApp extends UIComponent {

    public String title;
    public int width;
    public int height;
    public UIComponentArray uiComponentArray;

    public UIApp() {
    }

    public UIApp(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
    }

    public UIApp(String title, int width, int height, UIComponentArray uiComponentArray) {
        this.title = title;
        this.width = width;
        this.height = height;
        this.uiComponentArray = uiComponentArray;
    }
}
