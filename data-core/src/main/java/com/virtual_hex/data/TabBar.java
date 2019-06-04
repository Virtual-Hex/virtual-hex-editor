package com.virtual_hex.data;

public class TabBar implements UIData {

    public String label;
    public int flags;
    public UIDataList UIDataList;

    public TabBar() {
    }

    public TabBar(String label, UIDataList UIDataList) {
        this.label = label;
        this.UIDataList = UIDataList;
    }

    public TabBar(String label, int flags, UIDataList UIDataList) {
        this.label = label;
        this.flags = flags;
        this.UIDataList = UIDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TabBar that = (TabBar) o;

        if (flags != that.flags) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return UIDataList != null ? UIDataList.equals(that.UIDataList) : that.UIDataList == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (UIDataList != null ? UIDataList.hashCode() : 0);
        return result;
    }
}
