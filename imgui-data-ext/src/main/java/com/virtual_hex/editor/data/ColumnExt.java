package com.virtual_hex.editor.data;

public class ColumnExt extends StringLabel {

    public boolean hSeparatorTop;
    public boolean hSeparatorBottom;
    public boolean border;
    public UIComponents rows;

    public ColumnExt() {
    }

    public ColumnExt(String label, boolean hSeparatorTop, boolean hSeparatorBottom, UIComponents rows) {
        super(label);
        this.hSeparatorTop = hSeparatorTop;
        this.hSeparatorBottom = hSeparatorBottom;
        this.rows = rows;
    }

    public ColumnExt(String label, boolean hSeparatorTop, boolean hSeparatorBottom, boolean border, UIComponents rows) {
        super(label);
        this.hSeparatorTop = hSeparatorTop;
        this.hSeparatorBottom = hSeparatorBottom;
        this.border = border;
        this.rows = rows;
    }

    public ColumnExt(String label, boolean hSeparatorTop, boolean hSeparatorBottom, boolean border, ColumnExtRow... rows) {
        super(label);
        this.hSeparatorTop = hSeparatorTop;
        this.hSeparatorBottom = hSeparatorBottom;
        this.border = border;
        this.rows = new UIComponents(rows);
    }

    public ColumnExt(String label, UIComponents rows) {
        super(label);
        this.rows = rows;
    }

    public ColumnExt(String label, boolean border, UIComponents rows) {
        super(label);
        this.border = border;
        this.rows = rows;
    }

    public ColumnExt(String label, boolean border, ColumnExtRow... rows) {
        super(label);
        this.border = border;
        this.rows = new UIComponents(rows);
    }
}
