package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.ProgressBar;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = ProgressBar.class)
public class ProgressBarWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ProgressBar component = (ProgressBar) uiComponent;
        out.progressBar(component.fraction, component.width, component.height, component.overlay);
    }
}
