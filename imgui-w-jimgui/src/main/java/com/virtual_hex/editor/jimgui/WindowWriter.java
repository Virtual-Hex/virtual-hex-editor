package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.Window;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@UIComponentRegister(typeKey = Window.class)
public class WindowWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Window component = (Window) uiComponent;
        if (component.open) {
            // Not clipped or collapsed
            boolean visible = out.begin(component.label);
            if (visible) { // TODO Maybe remove this open check
                UIComponentWriter.processArray(out, component.uiComponents, writer);
            }
            JImGuiGen.end();
        }
    }
}
