package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.Button;
import com.virtual_hex.data.ComponentHandler;
import com.virtual_hex.data.UIComponent;
import com.virtual_hex.data.UIDataDeserializer;
import org.ice1000.jimgui.JImGui;

public enum ButtonHandler implements ComponentHandler<JImGui> {
    INSTANCE;

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDataDeserializer parentDeserializer) {
        Button uiComponent0 = (Button) uiComponent;
        ui.button(uiComponent0.label, uiComponent0.width, uiComponent0.height);
    }
}
