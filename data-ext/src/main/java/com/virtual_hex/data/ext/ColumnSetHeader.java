package com.virtual_hex.data.ext;


import com.virtual_hex.data.Text;
import com.virtual_hex.data.UIComponent;

public class ColumnSetHeader extends UIComponent {

    public String stringId;
    public UIComponent[] columns;
    public boolean border;
    public boolean headerSeparatorTop;
    public boolean headerSeparatorBottom;

    public ColumnSetHeader() {
    }

    public ColumnSetHeader(String stringId, boolean border, String... columns) {
        this.stringId = stringId;
        this.border = border;
        this.columns = new UIComponent[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }

    public ColumnSetHeader(String stringId, boolean border, UIComponent... columns) {
        this.stringId = stringId;
        this.columns = columns;
        this.border = border;
    }

    public ColumnSetHeader(String stringId, boolean border, boolean headerSeparatorTop, boolean headerSeparatorBottom, String... columns) {
        this.stringId = stringId;
        this.border = border;
        this.headerSeparatorTop = headerSeparatorTop;
        this.headerSeparatorBottom = headerSeparatorBottom;
        this.columns = new UIComponent[columns.length];
        for (int i = 0; i < columns.length; i++) {
            this.columns[i] = new Text(columns[i]);
        }
    }

    public ColumnSetHeader(String stringId, boolean border, boolean headerSeparatorTop, boolean headerSeparatorBottom, UIComponent... columns) {
        this.stringId = stringId;
        this.border = border;
        this.columns = columns;
        this.headerSeparatorTop = headerSeparatorTop;
        this.headerSeparatorBottom = headerSeparatorBottom;
    }

}
