package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentWriter;
import org.ice1000.jimgui.JImGui;

/**
 * Here we implement Component Writer as we are writing out to the screen
 */
public abstract class JImGuiComponentWriter implements UIComponentWriter<JImGui, DefaultUIWriter> {

    @Override
    public void dispose() {
        // Nothing Intended
    }
}
