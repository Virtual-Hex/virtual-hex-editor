package com.virtual_hex.editor.data;

public class Window extends OpenableStringLabel {

    public Window() {
    }

    public Window(String label, boolean open) {
        super(label, open);
    }

    public Window(String label, boolean open, UIComponents... components) {
        super(label, open, components);
    }

    public Window(String label, boolean open, UIComponent... components) {
        super(label, open, components);
    }
}
