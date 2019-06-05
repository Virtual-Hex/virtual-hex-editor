package com.virtual_hex.data;

public class TextInput extends UIComponent {

    public String label = "";
    public int flags;
    public String value;
    public int bufferSize;

    public TextInput() {
    }

    public TextInput(String label, int flags, String value, int bufferSize) {
        this.label = label;
        this.flags = flags;
        this.value = value;
        this.bufferSize = bufferSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextInput textInput = (TextInput) o;

        if (flags != textInput.flags) return false;
        if (bufferSize != textInput.bufferSize) return false;
        if (label != null ? !label.equals(textInput.label) : textInput.label != null) return false;
        return value != null ? value.equals(textInput.value) : textInput.value == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + bufferSize;
        return result;
    }
}
