package com.virtual_hex.editor.data;


/**
 * This class headFields must equal exactly that of how many cells there are. This means there can only
 * be the same amount of uiComponents in the header vs a row in the cells body. Now you can trick it, well
 * or do a trick and use a UIComponents and next multiple items into the one cell instead of on UIComponent
 */
public class ColumnExtRow extends UIComponents {

    public boolean separator;

    public ColumnExtRow() {
    }

    public ColumnExtRow(String... components) {
        super(components);
    }

    public ColumnExtRow(UIComponents... components) {
        super(components);
    }

    public ColumnExtRow(UIComponent... components) {
        super(components);
    }

    public ColumnExtRow(boolean separator, String... components) {
        super(components);
        this.separator = separator;
    }

    public ColumnExtRow(boolean separator, UIComponents... components) {
        super(components);
        this.separator = separator;
    }

    public ColumnExtRow(boolean separator, UIComponent... components) {
        super(components);
        this.separator = separator;
    }
}
