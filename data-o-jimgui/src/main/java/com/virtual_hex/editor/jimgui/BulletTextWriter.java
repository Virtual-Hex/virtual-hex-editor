package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.BulletText;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = BulletText.class)
public class BulletTextWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        BulletText component = (BulletText) uiComponent;
        out.bulletText(component.label);
    }
}
