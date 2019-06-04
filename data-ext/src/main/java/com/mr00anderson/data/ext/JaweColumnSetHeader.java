package com.mr00anderson.data.ext;


import com.mr00anderson.data.Text;

public class JaweColumnSetHeader {

    public String stringId;
    public Object[] columns;
    public boolean border;
    public boolean headerSeparatorTop;
    public boolean headerSeparatorBottom;

    public JaweColumnSetHeader() {
    }

    public JaweColumnSetHeader(String stringId, boolean border,  String... columns) {
        this.stringId = stringId;
        this.columns = columns;
        this.border = border;
        this.columns = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }


    public JaweColumnSetHeader(String stringId, boolean border,  Object[] columns) {
        this.stringId = stringId;
        this.columns = columns;
        this.border = border;
    }

    public JaweColumnSetHeader(String stringId, boolean border, boolean headerSeparatorTop, boolean headerSeparatorBottom, String... columns) {
        this.stringId = stringId;
        this.border = border;
        this.columns = columns;
        this.headerSeparatorTop = headerSeparatorTop;
        this.headerSeparatorBottom = headerSeparatorBottom;
        this.columns = new Object[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }

    public JaweColumnSetHeader(String stringId, boolean border, boolean headerSeparatorTop, boolean headerSeparatorBottom, Object[] columns) {
        this.stringId = stringId;
        this.border = border;
        this.columns = columns;
        this.headerSeparatorTop = headerSeparatorTop;
        this.headerSeparatorBottom = headerSeparatorBottom;
    }

}
