package com.virtual_hex.editor.jimgui;

import com.virtual_hex.editor.data.UIComponent;
import com.virtual_hex.editor.data.UIComponents;
import com.virtual_hex.editor.data.ColumnExt;
import com.virtual_hex.editor.data.ColumnExtRow;
import com.virtual_hex.editor.io.ComponentRegister;
import com.virtual_hex.editor.io.UIWriter;
import org.ice1000.jimgui.JImGui;

import java.util.List;

@ComponentRegister(typeKey = ColumnExt.class)
public class ColumnExtWriter extends JImGuiComponentWriter {

    @Override
    public void write(JImGui out, UIComponent uiComponent, UIWriter<JImGui> writer) {
        ColumnExt component = (ColumnExt) uiComponent;

        // Table Separator
        if (component.hSeparatorTop) out.separator();

        // Draw Columns
        ColumnExtRow columnExtRow = (ColumnExtRow) component.rows.uiComponents.get(0);
        if (columnExtRow == null) {
            return;
        }
        // Get column count
        int columnCount = columnExtRow.uiComponents.size();
        out.columns(columnCount, component.label, component.border);

        // Draw rows
        UIComponents rows = component.rows;
        UIComponentsWriter.processAddRemoveComponent(rows);

        // Loop through the rows here
        List<UIComponent> componentsList = component.rows.uiComponents;

        int size = componentsList.size();
        int index = size - 1;
        for (int i = 0; i < size; i++) {
            ColumnExtRow cRow = (ColumnExtRow) componentsList.get(i);
            UIComponentsWriter.processAddRemoveComponent(cRow);
            // Loop through each cell of the row
            List<UIComponent> cells = cRow.uiComponents;
            for (UIComponent cell : cells) {
                writer.write(out, cell);
                out.nextColumn();
            }
            if (cRow.separator && i != index) {
                out.separator();
            }
        }
        out.columns(1);

        // Table Separator
        if (component.hSeparatorBottom) out.separator();
    }
}
