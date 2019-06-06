package com.virtual_hex.data;

public class MenuItem extends UIComponent {

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
    public UiActivationHandler<MenuItem> onActivation;


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
        public UiActivationHandler<MenuItem> uiActivationHandler;

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

        public JaweMenuItemBuilder activationHandler(UiActivationHandler<MenuItem> uiActivationHandler) {
            this.uiActivationHandler = uiActivationHandler;
            return this;
        }

        public MenuItem build() {
            MenuItem menuItem = new MenuItem();
            menuItem.selected = this.selected;
            menuItem.onActivation = this.uiActivationHandler;
            menuItem.label = this.label;
            menuItem.shortcut = this.shortcut;
            menuItem.enabled = this.enabled;
            return menuItem;
        }
    }
}
