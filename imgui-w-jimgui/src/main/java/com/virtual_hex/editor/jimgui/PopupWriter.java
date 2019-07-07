package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.UIComponentWriter;
import com.virtual_hex.editor.data.Popup;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;
import org.ice1000.jimgui.JImStr;

@NativeExchange
@UIComponentRegister(typeKey = Popup.class)
public class PopupWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        Popup<JImStr> component = (Popup) uiComponent;
        // Not clipped or collapsed
        boolean visible = out.beginPopup(component.label, component.flags);
        if (visible) {
            UIComponentWriter.processArray(out, component.uiComponents, writer);
            JImGuiGen.endPopup();
        }
    }
}
