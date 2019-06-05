package com.virtual_hex.data;

public class Openable implements UIData{

    /**
     * Labels are unique see
     */
    public String label;

    /**
     * A native boolean which will be converted to a java boolean before worldWrapper serialization
     */
    public boolean open;

    /**
     * The window contents which can be any other Drawable, it will serialize like this class
     */
    public UIDataList uiDataList;

    public Type type;

    public Openable() {
    }

    public Openable(String label, boolean open, UIDataList uiDataList, Type type) {
        this.label = label;
        this.open = open;
        this.uiDataList = uiDataList;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Openable openable = (Openable) o;

        if (open != openable.open) return false;
        if (label != null ? !label.equals(openable.label) : openable.label != null) return false;
        if (uiDataList != null ? !uiDataList.equals(openable.uiDataList) : openable.uiDataList != null) return false;
        return type == openable.type;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (open ? 1 : 0);
        result = 31 * result + (uiDataList != null ? uiDataList.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public enum Type {
        WINDOW,
        WINDOW_EXITABLE,
        TAB_BAR,
        TAB_ITEM,
        TAB_ITEM_EXITABLE,
        COLLAPSABLE_HEADER,
        COLLAPSABLE_HEADER_EXITABLE;

    }
}
