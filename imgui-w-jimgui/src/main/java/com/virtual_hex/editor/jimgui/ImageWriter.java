package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.Image;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImTextureID;

@ComponentRegister(typeKey = Image.class)
public class ImageWriter extends JImGuiTextureComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Image component = (Image) uiComponent;
        JImTextureID textureId = getTextureId(component, writer);
        if(textureId != null) {
            out.image(textureId, component.width, component.height, component.uv0x, component.uv0y, component.uv1x, component.uv1y);
        } else {
            out.text(String.format("Image loading error. Could not load from type %s at %s.", component.from.getClass(), component.from.toString()));
        }
    }
}
