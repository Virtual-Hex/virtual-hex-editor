package com.virtual_hex.editor.data;

public class ListBox0 extends LabeledComponents  {

    public int width;
    public int height;

    public ListBox0() {
    }

    public ListBox0(String label, String... components) {
        super(label, Selectable.fromStrings(components));
    }

    public ListBox0(String label, int width, int height, String... components) {
        super(label, Selectable.fromStrings(components));
        this.width = width;
        this.height = height;
    }

    public ListBox0(String label, Selectable... components) {
        super(label, components);
    }

    public ListBox0(String label, int width, int height, Selectable... components) {
        super(label, components);
        this.width = width;
        this.height = height;
    }
}
