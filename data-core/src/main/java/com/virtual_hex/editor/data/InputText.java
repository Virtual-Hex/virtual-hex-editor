package com.virtual_hex.editor.data;

public class InputText extends UIComponent {

    public String label = "";
    public int flags;
    public String value;
    public int bufferSize;

    public InputText() {
    }

    public InputText(String label, int flags, String value, int bufferSize) {
        this.label = label;
        this.flags = flags;
        this.value = value;
        this.bufferSize = bufferSize;
    }

}
