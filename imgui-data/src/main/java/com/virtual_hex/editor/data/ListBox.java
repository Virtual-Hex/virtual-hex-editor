package com.virtual_hex.editor.data;

public class ListBox<LABEL> extends LabeledComponents<LABEL>  {

    public int itemsCount;
    public int heightInItems;

    public ListBox() {
    }

    public ListBox(LABEL label, int heightInItems, Selectable<LABEL>... components) {
        super(label, components);
        this.itemsCount = components.length;
        this.heightInItems = heightInItems;
    }

    public ListBox(LABEL label, int itemsCount, int heightInItems, Selectable<LABEL>... components) {
        super(label, components);
        this.itemsCount = itemsCount;
        this.heightInItems = heightInItems;
    }
}
