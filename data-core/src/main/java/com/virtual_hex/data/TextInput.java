package com.virtual_hex.data;

public class TextInput implements UIData {

    public String label = "";
    public int flags;
    public String value;

    public TextInput() {
    }

    public TextInput(String label, int flags, String value) {
        this.label = label;
        this.flags = flags;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextInput that = (TextInput) o;

        if (flags != that.flags) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
