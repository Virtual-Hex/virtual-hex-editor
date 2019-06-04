package com.mr00anderson.data;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 *
 *   button behavior without the visuals, frequently useful to build custom behaviors using the public api (along with IsItemActive, IsItemHovered, etc.)
 */
public class InvisibleButton {

    public String label;
    public int width;
    public int height;

    /**
     * This will be triggered when open or deselected
     */
    public ActivationHandler<InvisibleButton> onActivation = (imGuiDrawable, parentDrawable) -> {};

    public InvisibleButton() {
    }

    public InvisibleButton(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvisibleButton that = (InvisibleButton) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        return onActivation != null ? onActivation.equals(that.onActivation) : that.onActivation == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (onActivation != null ? onActivation.hashCode() : 0);
        return result;
    }
}
