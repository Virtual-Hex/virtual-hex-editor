package com.virtual_hex.data;

public class BeginTabItem implements UIData {

   public String label;

   public UIDataList UIDataList;

    public BeginTabItem() {
    }

    public BeginTabItem(String label, UIDataList UIDataList) {
        this.label = label;
        this.UIDataList = UIDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeginTabItem that = (BeginTabItem) o;

        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        return result;
    }
}
