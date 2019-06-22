package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.PlotLines;
import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

@ComponentRegister(typeKey = PlotLines.class)
public class PlotLinesWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        PlotLines component = (PlotLines) uiComponent;
        out.plotLines(component.label, component.values, component.valueOffset, component.valuesLength, component.overlayText, component.scaleMin, component.scaleMax, component.graphWidth, component.graphHeight);
    }
}
