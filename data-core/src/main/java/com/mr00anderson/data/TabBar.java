package com.mr00anderson.data;

public class TabBar {

    public String label;
    public int flags;
    public Drawables drawables;

    public TabBar() {
    }

    public TabBar(String label, Drawables drawables) {
        this.label = label;
        this.drawables = drawables;
    }

    public TabBar(String label, int flags, Drawables drawables) {
        this.label = label;
        this.flags = flags;
        this.drawables = drawables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TabBar that = (TabBar) o;

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
