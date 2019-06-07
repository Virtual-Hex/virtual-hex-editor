package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.UIApp;
import com.virtual_hex.data.UIComponent;
import com.virtual_hex.handling.ComponentHandler;
import com.virtual_hex.handling.UIDeserializer;
import org.ice1000.jimgui.JImGui;

public class UIAppHandler implements ComponentHandler<JImGui> {

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDeserializer<JImGui> parentDeserializer) {
        UIApp drawable = (UIApp) uiComponent;
        parentDeserializer.draw(ui, drawable.uiComponentArray);
    }
}
