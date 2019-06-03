package com.mr00anderson.jawe;

import com.mr00anderson.jawe.drawables.JaweText;

public class JaweColumnSetRow {

    // this must be divisible by the JaweColumnSetHeader
    public Object[] columns;

    public JaweColumnSetRow() {
    }

    public JaweColumnSetRow( String... columns) {
        this.columns = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new JaweText(columns[i]);
        }
    }
    public JaweColumnSetRow(Object[] columns) {
        this.columns = columns;
    }

}
