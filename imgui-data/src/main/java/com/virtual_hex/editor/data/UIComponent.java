package com.virtual_hex.editor.data;

import java.util.UUID;

public interface UIComponent {

    UIComponent EMPTY_COMPONENT = () -> UUID.fromString("");
    UIComponent[] EMPTY_COMPONENTS = new UIComponent[0];

    UUID getId();
}
