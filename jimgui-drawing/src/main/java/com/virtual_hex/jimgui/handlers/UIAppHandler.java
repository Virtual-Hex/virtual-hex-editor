package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.*;
import org.ice1000.jimgui.JImGui;

public enum UIAppHandler implements ComponentHandler<JImGui> {
    INSANCE;

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDataDeserializer parentDeserializer) {
        UIApp drawable = (UIApp) uiComponent;
        UIDataDeserializer deserializer = drawable.deserializerWrapper.deserializer;
        deserializer.draw(ui, drawable.deserializerWrapper.uiComponent, deserializer);
    }
}
