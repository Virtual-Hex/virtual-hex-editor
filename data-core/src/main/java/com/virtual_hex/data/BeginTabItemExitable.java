package com.virtual_hex.data;

public class BeginTabItemExitable implements UIData{

   public String label;
   public int flags;
   public boolean open;
   public UIDataList UIDataList;

    public BeginTabItemExitable() {
    }

    public BeginTabItemExitable(String label, int flags, boolean open, UIDataList UIDataList) {
        this.label = label;
        this.flags = flags;
        this.open = open;
        this.UIDataList = UIDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeginTabItemExitable that = (BeginTabItemExitable) o;

        if (flags != that.flags) return false;
        if (open != that.open) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (open ? 1 : 0);
        return result;
    }
}
