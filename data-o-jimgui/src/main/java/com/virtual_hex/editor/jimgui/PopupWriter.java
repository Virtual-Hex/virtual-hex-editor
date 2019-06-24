package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.Popup;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIComponentsUtils;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImGuiGen;

@NativeExchange
@ComponentRegister(typeKey = Popup.class)
public class PopupWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        Popup component = (Popup) uiComponent;
        // Not clipped or collapsed
        boolean visible = out.beginPopup(component.label, component.flags);
        if (visible) {
            UIComponentsUtils.processUiDataList(out, component, writer);
            JImGuiGen.endPopup();
        }
    }
}
