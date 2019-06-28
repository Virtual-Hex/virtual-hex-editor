package com.virtual_hex.editor.data;

public class ShowStyleEditor<T> extends AbstractUIComponent {
    public T style;

    public ShowStyleEditor() {
    }

    public ShowStyleEditor( T style) {
        this.style = style;
    }
}
