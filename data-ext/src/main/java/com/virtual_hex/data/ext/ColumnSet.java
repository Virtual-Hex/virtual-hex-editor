package com.virtual_hex.data.ext;

import com.virtual_hex.data.UIData;

public class ColumnSet implements UIData {

    public ColumnSetHeader header;
    public ColumnSetBody body;

    public ColumnSet() {
    }

    public ColumnSet(ColumnSetHeader header, ColumnSetBody body) {
        this.header = header;
        this.body = body;
    }
}
