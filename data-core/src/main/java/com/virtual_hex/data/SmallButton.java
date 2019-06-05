package com.virtual_hex.data;

/**
 * button with FramePadding=(0,0) to easily embed within text
 *
 * https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L395
 *
 * returning true when pressed and triggers activation handler
 *
 */
public class SmallButton extends UIComponent {

    public String label;

    /**
     * This will be triggered when open or deselected
     */
    public ActivationHandler<SmallButton> onActivation = (imGuiDrawable, parentDrawable) -> {};

    public SmallButton() {
    }

    public SmallButton(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmallButton that = (SmallButton) o;

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
