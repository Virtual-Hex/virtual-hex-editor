package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;


/**
 * TODO Window Utilities from, probably just a API note that they can be  used
 * inside begin() end() so within he provided window contents
 *
 *  https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L258
 *
 *  Returns false indicating collapsed or fully clipped
 *  @See https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L245
 *
 *
 */
public class JaweWindow {

    /**
     * Labels are unique see
     */
    public String label;

    /**
     * A native boolean which will be converted to a java boolean before worldWrapper serialization
     */
    public boolean open;

    /**
     * {@link org.ice1000.jimgui.flag.JImWindowFlags}
     */
    public int flags;

    /**
     * The window contents which can be any other Drawable, it will serialize like this class
     */
    public JaweDrawables drawables;

    /**
     * This will be called when the window is not collapsed or not fully clipped,
     * this is used if extra logic should occur outside the windowContents
     *
     *  @See https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L245
     */
    public ActivationHandler<JaweWindow> onActivation = (imGuiDrawable, parentDrawable) -> {};

    public JaweWindow() {
    }

    public JaweWindow(String label, boolean open, int flags, JaweDrawables drawables) {
        this.label = label;
        this.open = open;
        this.flags = flags;
        this.drawables = drawables;
    }

    public JaweWindow(String label, boolean open, int flags, JaweDrawables drawables, ActivationHandler<JaweWindow> onActivation) {
        this.label = label;
        this.open = open;
        this.flags = flags;
        this.drawables = drawables;
        this.onActivation = onActivation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JaweWindow that = (JaweWindow) o;

        if (open != that.open) return false;
        if (flags != that.flags) return false;
        return label != null ? label.equals(that.label) : that.label == null;
    }

    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (open ? 1 : 0);
        result = 31 * result + flags;
        return result;
    }
}
