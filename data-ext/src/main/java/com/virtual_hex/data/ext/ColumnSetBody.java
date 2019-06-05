package com.virtual_hex.data.ext;

import com.virtual_hex.data.UIComponent;

public class ColumnSetBody extends UIComponent {

    // this must be divisible by the ColumnSetHeader
    public ColumnSetRow[] rows;

    // TODO horizontal seps
    public boolean seperators;

    public ColumnSetBody() {
    }

    public ColumnSetBody(ColumnSetRow... rows) {
        this.rows = rows;
    }
}
