package com.virtual_hex.data;

// We could just merge this to openable
public class OpenableFlags extends Openable  {

    public int flags;

    public OpenableFlags() {
    }

    public OpenableFlags(String label, boolean open, UIComponentArray uiDataArray, Type type, int flags) {
        super(label, open, uiDataArray, type);
        this.flags = flags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OpenableFlags that = (OpenableFlags) o;

        return flags == that.flags;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + flags;
        return result;
    }
}
