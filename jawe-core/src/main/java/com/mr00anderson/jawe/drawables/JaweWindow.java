package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import com.mr00anderson.jawe.utils.JaweUtils;
import org.ice1000.jimgui.JImGui;
import org.ice1000.jimgui.NativeBool;

@PooledWeaver
public class JaweWindow extends AbstractJaweDrawable {

    public String label;
    public NativeBool open;
    public int flags;
    public JaweDrawable windowContents = JaweUtils.EMPTY_DRAWABLE;

    public JaweWindow() {
    }

    @Override
    public void draw(JImGui imGui, World world) {
        draw0(imGui.begin(label, open, flags));
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
        public boolean open;
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
            jaweWindow.onActivation = this.onActivation;
            jaweWindow.flags = this.flags;
            return jaweWindow;
        }
    }
}
