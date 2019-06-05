package com.virtual_hex.data;

/**
 * Must be registered with ClazzDrawer
 */
public class UIDeserializerWrapper extends UIData {

    public UIDataDeserializer deserializer;
    public UIData uiData;

    public UIDeserializerWrapper() {
    }

    public UIDeserializerWrapper(UIDataDeserializer deserializer, UIData uiData) {
        this.deserializer = deserializer;
        this.uiData = uiData;
    }
}
