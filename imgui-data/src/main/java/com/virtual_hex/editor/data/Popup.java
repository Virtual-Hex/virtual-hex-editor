package com.virtual_hex.editor.data;

/**
 * // call to mark popup as open (don't call every frame!). popups are closed when user click outside, or if CloseCurrentPopup() is called within a BeginPopup()/EndPopup() block. By default, Selectable()/MenuItemSelectable() are calling CloseCurrentPopup(). Popup identifiers are relative to the current ID-stack (so Popup and BeginPopup needs to be at the same level).
 */
public class Popup<LABEL> extends LabeledComponents<LABEL> {

    public int flags;

    public Popup() {
    }

    public Popup(LABEL label) {
        super(label);
    }

    public Popup(LABEL label, UIComponents... components) {
        super(label, components);
    }

    public Popup(LABEL label, UIComponent... components) {
        super(label, components);
    }

    public Popup(LABEL label, int flags) {
        super(label);
        this.flags = flags;
    }

    public Popup(LABEL label, int flags, UIComponents... components) {
        super(label, components);
        this.flags = flags;
    }

    public Popup(LABEL label, int flags, UIComponent... components) {
        super(label, components);
        this.flags = flags;
    }
}
