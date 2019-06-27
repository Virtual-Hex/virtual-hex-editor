package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.ComponentRegister;
import com.virtual_hex.editor.data.ProgressBar;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ProgressBar.class)
public class ProgressBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ProgressBar component = (ProgressBar) uiComponent;
        out.progressBar(component.fraction, component.width, component.height, component.overlay);
    }
}
