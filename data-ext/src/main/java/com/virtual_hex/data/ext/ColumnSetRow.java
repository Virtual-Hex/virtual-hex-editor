package com.virtual_hex.data.ext;

import com.virtual_hex.data.Text;
import com.virtual_hex.data.UIData;

public class ColumnSetRow extends UIData {

    // this must be divisible by the ColumnSetHeader
    // TODO should be a drawbles, need to have safty for adding
    public UIData[] columns;

    public ColumnSetRow() {
    }

    public ColumnSetRow(String... columns) {
        this.columns = new UIData[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }

    public ColumnSetRow(UIData[] columns) {
        this.columns = columns;
    }


}
