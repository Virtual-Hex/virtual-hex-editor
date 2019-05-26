package com.mr00anderson.jawe.drawables;

import com.artemis.World;
import com.artemis.annotations.PooledWeaver;
import com.mr00anderson.jawe.handlers.ActivationHandler;
import org.ice1000.jimgui.JImGui;

@PooledWeaver
public class JaweMenuItem extends AbstractJaweDrawable {

    public String label;
    public String shortcut;
    public boolean selected;
    public boolean enabled;

    @Override
    public void draw(JImGui imGui, World world) {
        draw0(imGui.menuItem(label, shortcut, selected, enabled));
    }

    @Override
    public void dispose() {
        // No native or allocated objects to dispose
    }

    public static final class JaweMenuItemBuilder {
        public String label;
        public String shortcut;
        public boolean selected;
        public boolean enabled;
        public ActivationHandler<JaweMenuItem> activationHandler;

        private JaweMenuItemBuilder() {
        }

        public static JaweMenuItemBuilder aJaweMenuItem() {
            return new JaweMenuItemBuilder();
        }

        public JaweMenuItemBuilder label(String label) {
            this.label = label;
            return this;
        }

        public JaweMenuItemBuilder shortcut(String shortcut) {
            this.shortcut = shortcut;
            return this;
        }

        public JaweMenuItemBuilder selected(boolean selected) {
            this.selected = selected;
            return this;
        }

        public JaweMenuItemBuilder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public JaweMenuItemBuilder activationHandler(ActivationHandler<JaweMenuItem> activationHandler) {
            this.activationHandler = activationHandler;
            return this;
        }

        public JaweMenuItem build() {
            JaweMenuItem jaweMenuItem = new JaweMenuItem();
            jaweMenuItem.selected = this.selected;
            jaweMenuItem.onActivation = this.activationHandler;
            jaweMenuItem.label = this.label;
            jaweMenuItem.shortcut = this.shortcut;
            jaweMenuItem.enabled = this.enabled;
            return jaweMenuItem;
        }
    }
}
