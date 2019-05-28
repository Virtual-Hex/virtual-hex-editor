package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.utils.JaweUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

@PooledWeaver
public class JaweWindow extends JaweComponent {

    // TODO Inject this and the world if possible and work on converting drawble to a functional interface
    public JImGui imGui;

    public String label;
    public NativeBool open = JaweUtils.createBool(true);
    public int flags = 0;
    public ActivationHandler<JaweWindow> onActivation;

    public JaweDrawable windowContents;

    public JaweWindow() {
    }


    @Override
    public void draw(JImGui imGui, World world) {
        if(imGui.begin(label, open, flags) && onActivation != null) {
            onActivation.handle(this);
        }

        windowContents.draw(imGui, world);
        imGui.end();
    }

    @Override
    public void dispose() {
        open.deallocateNativeObject();
        windowContents.dispose();
        onActivation.dispose();
    }


    public static final class JaweWindowBuilder {
        public String label;
        public boolean open = true;
        public int flags;
        public JaweDrawable windowContents;
        public ActivationHandler<JaweWindow> onActivation;

        private JaweWindowBuilder() {
        }

        public static JaweWindowBuilder aJaweWindow() {
            return new JaweWindowBuilder();
        }

        public JaweWindowBuilder label(String label) {
            this.label = label;
            return this;
        }

        public JaweWindowBuilder open(boolean open) {
            this.open = open;
            return this;
        }

        public JaweWindowBuilder flags(int flags) {
            this.flags = flags;
            return this;
        }

        public JaweWindowBuilder windowContents(JaweDrawable windowContents) {
            this.windowContents = windowContents;
            return this;
        }

        public JaweWindowBuilder onActivation(ActivationHandler<JaweWindow> onActivation) {
            this.onActivation = onActivation;
            return this;
        }

        public JaweWindow build() {
            JaweWindow jaweWindow = new JaweWindow();
            jaweWindow.label = this.label;
            jaweWindow.open = JaweUtils.createBool(open);
            jaweWindow.windowContents = this.windowContents;
            jaweWindow.flags = this.flags;
            jaweWindow.onActivation = this.onActivation;
            return jaweWindow;
        }
    }
}
