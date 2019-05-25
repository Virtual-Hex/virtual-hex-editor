package com.mr00anderson.editor.types;

import com.artemis.World;
import com.mr00anderson.editor.jimgui.JImGuiDrawable;
import com.mr00anderson.editor.jimgui.handlers.ActivationHandler;
import com.mr00anderson.editor.utils.NativeUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

public class JImGuiWindowDrawable extends AbstractJImGuiDrawable {

    public String label;
    public NativeBool open;
    public int flags;
    public JImGuiDrawable windowContents;

    public JImGuiWindowDrawable(ActivationHandler activationHandler) {
        super(activationHandler);
    }

    public JImGuiWindowDrawable() {
    }

    public JImGuiWindowDrawable(String label, JImGuiDrawable windowContents) {
        this(label, 0, windowContents);
    }

    public JImGuiWindowDrawable(String label, int flags, JImGuiDrawable windowContents) {
        this(label, NativeUtils.createBool(true), flags, windowContents);
    }

    public JImGuiWindowDrawable(String label, NativeBool open, int flags, JImGuiDrawable windowContents) {
        this.label = label;
        this.open = open;
        this.flags = flags;
        this.windowContents = windowContents;
    }

    @Override
    public void draw(JImGui imGui, World world) {
        imGui.begin(label, open, flags);
        windowContents.draw(imGui, world);
        imGui.end();
        activationHandler.handle(this);
    }

    @Override
    public void dispose() {

    }
}
