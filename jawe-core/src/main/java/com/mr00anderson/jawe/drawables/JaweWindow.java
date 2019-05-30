package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.JaweJimGuiStaticDeallocateManager;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;


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
public class JaweWindow implements JaweDrawable {

    /**
     * Labels are unique see
     */
    public String label;

    /**
     * A native boolean which will be converted to a java boolean before world serialization
     */
    public NativeBool open = JaweJimGuiStaticDeallocateManager.createBool(true);

    /**
     * {@link org.ice1000.jimgui.flag.JImWindowFlags}
     */
    public int flags;

    /**
     * The window contents which can be any other JaweDrawable, it will serialize like this class
     */
    public JaweDrawable windowContents;

    /**
     * This will be called when the window is not collapsed or not fully clipped,
     * this is used if extra logic should occur outside the windowContents
     *
     *  @See https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L245
     */
    public ActivationHandler<JaweWindow> onActivation = imGuiDrawable -> {};

    public JaweWindow() {
    }

    @Override
    public void draw(JImGui imGui) {
        if(imGui.begin(label, open, flags)) {
            windowContents.draw(imGui);
            onActivation.handle(this);
        }
        imGui.end();
    }
}
