package com.virtual_hex.editor.jimgui;


import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

import java.util.List;

/**
 * Here we implement Component Writer as we are writing out to the screen
 */
public abstract class JImGuiComponentWriter implements UIComponentWriter<JImGui, DefaultUIWriter> {

    @Override
    public void dispose() {
        // Nothing Intended
    }

    /**
     * Do not use on LinkedList
     *
     * @param components
     */
    public static void processList(JImGui out, DefaultUIWriter writer, List<UIComponent> components){
        int size = components.size();
        // Must be index for removal and add operations to be while list is being iterated
        for (int i = 0; i < size; i++) {
            UIComponent uiComponent = components.get(i);
            writer.write(out, uiComponent);
        }
    }
}
