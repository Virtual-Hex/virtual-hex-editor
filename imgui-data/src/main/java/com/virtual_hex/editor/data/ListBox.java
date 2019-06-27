package com.virtual_hex.editor.data;

public class ListBox extends LabeledComponents  {

    public int itemsCount;
    public int heightInItems;

    public ListBox() {
    }

    public ListBox(String label, int heightInItems, String... components) {
        super(label, Selectable.fromStrings(components));
        this.itemsCount = components.length;
        this.heightInItems = heightInItems;
    }

    public ListBox(String label, int itemsCount, int heightInItems, String... components) {
        super(label, Selectable.fromStrings(components));
        this.itemsCount = itemsCount;
        this.heightInItems = heightInItems;
    }

    public ListBox(String label, int heightInItems, Selectable... components) {
        super(label, components);
        this.itemsCount = components.length;
        this.heightInItems = heightInItems;
    }

    public ListBox(String label, int itemsCount, int heightInItems, Selectable... components) {
        super(label, components);
        this.itemsCount = itemsCount;
        this.heightInItems = heightInItems;
    }
}
