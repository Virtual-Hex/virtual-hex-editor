package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.SliderVec4;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.JImStr;
import org.ice1000.jimgui.JImVec4;

@NativeExchange
@UIComponentRegister(typeKey = SliderVec4.class)
public class SliderVec4Writer extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        SliderVec4<JImStr, JImVec4> component = (SliderVec4) uiComponent;
        out.sliderVec4(component.label, component.value, component.valueMin, component.valueMax);
        writer.handleStateChange(out, component, writer);
    }
}
