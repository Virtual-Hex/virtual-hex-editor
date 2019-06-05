package com.virtual_hex.data;

/**
 * Must be registered with ClazzDrawer
 */
public class UIDeserializerWrapper extends UIComponent {

    public UIDataDeserializer deserializer;
    public UIComponent uiComponent;

    public UIDeserializerWrapper() {
    }

    public UIDeserializerWrapper(UIDataDeserializer deserializer, UIComponent uiComponent) {
        this.deserializer = deserializer;
        this.uiComponent = uiComponent;
    }
}
