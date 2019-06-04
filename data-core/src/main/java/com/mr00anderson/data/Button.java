package com.mr00anderson.data;

/**
 *  Button
 *
 *  returning true when pressed
 *
 *  https://github.com/ocornut/imgui/blob/cb7ba60d3f7d691c698c4a7499ed64757664d7b8/imgui.h#L394
 */
public class Button {

    public String label;
    public int width;
    public int height;

    /**
     * This will be triggered when open or deselected
     */
    public ActivationHandler<Button> onActivation = (imGuiDrawable, parentDrawable) -> {};

    public Button() {
    }

    public Button(String label) {
        this.label = label;
    }

    public Button(String label, int width, int height) {
        this.label = label;
        this.width = width;
        this.height = height;
    }

    public Button(String label, ActivationHandler<Button> onActivation) {
        this.label = label;
        this.onActivation = onActivation;
    }

    public Button(String label, int width, int height, ActivationHandler<Button> onActivation) {
        this.label = label;
        this.width = width;
        this.height = height;
        this.onActivation = onActivation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Button that = (Button) o;

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
