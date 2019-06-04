package com.mr00anderson.data.ext;

import com.mr00anderson.data.Text;

public class JaweColumnSetRow {

    // this must be divisible by the JaweColumnSetHeader
    // TODO should be a drawbles, need to have safty for adding
    public Object[] columns;

    public JaweColumnSetRow() {
    }

    public JaweColumnSetRow(String... columns) {
        this.columns = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }
    public JaweColumnSetRow(Object[] columns) {
        this.columns = columns;
    }

}
