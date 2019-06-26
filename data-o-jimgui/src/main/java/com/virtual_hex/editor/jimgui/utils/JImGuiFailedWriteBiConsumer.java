package com.virtual_hex.editor.jimgui.utils;

import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

import java.util.function.BiConsumer;

public class JImGuiFailedWriteBiConsumer implements BiConsumer<JImGui, UIComponent> {

    @Override
    public void accept(JImGui imGui, UIComponent uiComponent) {
        String format = String.format("Cannot find a data writer. Class: %s, Id: %s", uiComponent.getClass(), uiComponent.getId());
        imGui.text(format);
    }
}
