package com.virtual_hex.jimgui.handlers;

import com.virtual_hex.data.Button;
import com.virtual_hex.data.UIComponent;
import com.virtual_hex.handling.ComponentHandler;
import com.virtual_hex.handling.UIDeserializer;
import org.ice1000.jimgui.JImGui;

public class ButtonHandler implements ComponentHandler<JImGui> {

    @Override
    public void draw(JImGui ui, UIComponent uiComponent, UIDeserializer parentDeserializer) {
        Button uiComponent0 = (Button) uiComponent;
        boolean button = ui.button(uiComponent0.label, uiComponent0.width, uiComponent0.height);
        if(button){
            parentDeserializer.handle(uiComponent, parentDeserializer);
        }

    }
}
