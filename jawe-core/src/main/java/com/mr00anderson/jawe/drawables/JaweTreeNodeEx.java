package com.mr00anderson.jawe.drawables;

/**
 * TreeNode functions return true when the node is open, in which case you need to also call TreePop() when you are finished displaying the tree node contents.
 */
public class JaweTreeNodeEx {

    public String label;
    public int flags;
    public JaweDrawables drawables; // TODO Look at this, this could be and in a lot of cases be singular ? is it a peformance issue probably not for now

    public JaweTreeNodeEx() {
    }

    public JaweTreeNodeEx(String label, int flags, JaweDrawables drawables) {
        this.label = label;
        this.flags = flags;
        this.drawables = drawables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweTreeNodeEx that = (JaweTreeNodeEx) o;

        if (flags != that.flags) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return drawables != null ? drawables.equals(that.drawables) : that.drawables == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + flags;
        result = 31 * result + (drawables != null ? drawables.hashCode() : 0);
        return result;
    }
}
