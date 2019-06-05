package com.virtual_hex.data.ext;

import com.virtual_hex.data.Text;
import com.virtual_hex.data.UIComponent;

public class ColumnSetRow extends UIComponent {

    // this must be divisible by the ColumnSetHeader
    // TODO should be a drawbles, need to have safty for adding
    public UIComponent[] columns;

    public ColumnSetRow() {
    }

    public ColumnSetRow(String... columns) {
        this.columns = new UIComponent[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }

    public ColumnSetRow(UIComponent[] columns) {
        this.columns = columns;
    }


}
