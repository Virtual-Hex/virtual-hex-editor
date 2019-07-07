package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.UIComponentRegister;
import com.virtual_hex.editor.data.PlotHistogram;
import com.virtual_hex.editor.data.UIComponent;
import org.ice1000.jimgui.JImGui;

@UIComponentRegister(typeKey = PlotHistogram.class)
public class PlotHistogramWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, DefaultUIWriter writer) {
        PlotHistogram component = (PlotHistogram) uiComponent;
        out.plotHistogram(component.label, component.values, component.valueOffset, component.valuesLength, component.overlayText, component.scaleMin, component.scaleMax, component.graphWidth, component.graphHeight);
    }
}
