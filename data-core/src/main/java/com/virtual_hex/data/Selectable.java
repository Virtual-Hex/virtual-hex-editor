package com.virtual_hex.data;

public class Selectable<T extends Selectable> implements UIData {

    public String label;
    public boolean selected;
    public int flags;
    public int width;
    public int height;

    /**
     * This will be triggered when open or deselected
     */
    public ActivationHandler<T> onActivation = (imGuiDrawable, parentDrawable) -> {};

    public Selectable() {
    }

    public Selectable(String label) {
        this.label = label;
    }

    public Selectable(String label, int flags) {
        this.label = label;
        this.flags = flags;
    }

    public Selectable(String label, ActivationHandler<T> onActivation) {
        this.label = label;
        this.onActivation = onActivation;
    }

    public Selectable(String label, int flags, ActivationHandler<T> onActivation) {
        this.label = label;
        this.flags = flags;
        this.onActivation = onActivation;
    }

    public Selectable(String label, boolean selected, ActivationHandler<T> onActivation) {
        this.label = label;
        this.selected = selected;
        this.onActivation = onActivation;
    }

    public Selectable(String label, boolean selected, int flags, ActivationHandler<T> onActivation) {
        this.label = label;
        this.selected = selected;
        this.flags = flags;
        this.onActivation = onActivation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Selectable that = (Selectable) o;

        if (selected != that.selected) return false;
        if (flags != that.flags) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return onActivation != null ? onActivation.equals(that.onActivation) : that.onActivation == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (selected ? 1 : 0);
        result = 31 * result + flags;
        result = 31 * result + (onActivation != null ? onActivation.hashCode() : 0);
        return result;
    }
}
