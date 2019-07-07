package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.ProgressBar;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = ProgressBar.class)
public class ProgressBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        ProgressBar component = (ProgressBar) uiComponent;
        out.progressBar(component.fraction, component.width, component.height, component.overlay);
    }
}
