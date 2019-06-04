package com.virtual_hex.data;

/**
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
public class TreeNodeEx implements UIData {

    public String label;
    public int flags;
    public UIDataList UIDataList; // TODO Look at this, this could be and in a lot of cases be singular ? is it a peformance issue probably not for now

    public TreeNodeEx() {
    }

    public TreeNodeEx(String label, int flags, UIDataList UIDataList) {
        this.label = label;
        this.flags = flags;
        this.UIDataList = UIDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNodeEx that = (TreeNodeEx) o;

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
