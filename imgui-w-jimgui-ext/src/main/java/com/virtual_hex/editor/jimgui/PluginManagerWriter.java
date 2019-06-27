package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.EnhancedPluginManager;
import com.virtual_hex.editor.PluginManager;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = EnhancedPluginManager.class)
public class PluginManagerWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        PluginManager manager = (EnhancedPluginManager) uiComponent;

    }
}
