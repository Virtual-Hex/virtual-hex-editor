package com.mr00anderson.jawe.drawables;

import com.mr00anderson.jawe.handlers.ActivationHandler;

public class JaweMenuItem  {

    /**
     * Menu item name
     */
    public String label;

    /**
     * These are not currently processed by ImGui, JImGui or Jawe
     */
    public String shortcut;

    /**
     * If the menu item is open or not
     */
    public boolean selected;

    /**
     * If the menu item is enabled
     */
    public boolean enabled;

    /**
     *
     */
    public ActivationHandler<JaweMenuItem> onActivation;


//    public void draw(JImGui imGui) {
//        // Returns true on activation + toggle
//        // @see https://github.com/ocornut/imgui/blob/70d9f79312233622a4f9e683177105a226b27b8c/imgui.h#L535
//        if(imGui.menuItem(label, shortcut, selected, enabled) && onActivation != null){
//            onActivation.handle(this);
//        }
//    }

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
