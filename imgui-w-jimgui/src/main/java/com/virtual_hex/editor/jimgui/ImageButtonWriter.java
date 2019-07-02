package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ImageButton;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImTextureID;

@ComponentRegister(typeKey = ImageButton.class)
public class ImageButtonWriter extends JImGuiTextureComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ImageButton<JImStr, Object> component = (ImageButton) uiComponent;
        JImTextureID textureId = getTextureId(component, writer);
        if(textureId != null) {
            boolean pressed = out.imageButton(textureId, component.width, component.height, component.uv0x, component.uv0y, component.uv1x, component.uv1y, component.framePadding);
            if(pressed) {
                writer.handleActivation(out, uiComponent, writer);
            }
        } else {
            out.text(String.format("Image loading error. Could not load from type %s at %s.", component.from.getClass(), component.from.toString()));
        }
    }
}
