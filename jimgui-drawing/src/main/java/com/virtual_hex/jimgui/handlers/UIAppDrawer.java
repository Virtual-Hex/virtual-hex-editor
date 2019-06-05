package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.TypeDrawer;
import com.virtual_hex.data.UIComponent;
import com.virtual_hex.data.UIDataDeserializer;
import com.virtual_hex.data.UIDeserializerWrapper;
import org.ice1000.jimgui.JImGui;

public enum  UIAppDrawer implements TypeDrawer<JImGui> {
    INSANCE;

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDataDeserializer parentDeserializer) {
        UIDeserializerWrapper drawable = (UIDeserializerWrapper) uiComponent;
        UIDataDeserializer deserializer = drawable.deserializer;
        deserializer.draw(ui, drawable.uiComponent, deserializer);
    }
}
